package com.example.spacegame

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView


class GameView : SurfaceView, Runnable {

    var playing = false
    var gameThread: Thread? = null
    lateinit var surfaceHolder: SurfaceHolder
    lateinit var canvas: android.graphics.Canvas

    lateinit var paint: Paint
    var stars = arrayListOf<Star>()
    var enemies = arrayListOf<Enemy>()
    var bullets = arrayListOf<Bullet>()

    private var lastBulletTime = 0L  // Guarda o tempo do último disparo


    lateinit var player: Player
    lateinit var boom: Boom

    val musicPlayer = MediaPlayer.create(context, R.raw.background_song)
    private lateinit var boomSoundPlayer: BoomSoundPlayer
    private lateinit var bulletSoundPlayer: BulletSoundPlayer

    private fun init(context: Context, width: Int, height: Int) {
        surfaceHolder = holder
        paint = Paint()

        boomSoundPlayer = BoomSoundPlayer(context)
        bulletSoundPlayer = BulletSoundPlayer(context)

        for (i in 0..100) {
            stars.add(Star(width, height))
        }
        player = Player(context, width, height)

        for (i in 0..3) {
            enemies.add(Enemy(context, width, height))
        }
        boom = Boom(context, width, height)

        for (i in 0..5) {
            bullets.add(Bullet(context, width, height))
        }
    }

    constructor(context: Context?, width: Int, height: Int) : super(context) {
        init(context!!, width, height)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context!!, 0, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context!!, 0, 0)
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread?.start()

        musicPlayer.start()
        musicPlayer.setLooping(true)
    }

    fun pause() {
        playing = false
        gameThread?.join()
        musicPlayer.pause()
        boomSoundPlayer.release()
    }

    override fun run() {
        while (playing) {
            update()
            draw()
            control()
        }
    }

    fun update() {
        // Resetar posição do efeito de explosão
        boom.x = -300
        boom.y = -300

        // Atualiza a posição de todas as estrelas
        for (s in stars) {
            s.update(player.speed)
        }

        // Atualiza o jogador
        player.update()

        val bulletsToRemove = mutableListOf<Bullet>()

        for (bullet in bullets) {
            bullet.update()

            for (enemy in enemies) {
                if (Rect.intersects(bullet.detectCollision, enemy.detectCollision)) {
                    // Reproduz o som de explosão
                    boomSoundPlayer.playSound()

                    // Exibe o efeito de explosão na posição do inimigo
                    boom.x = enemy.x
                    boom.y = enemy.y

                    // Marca a bala para ser removida
                    bulletsToRemove.add(bullet)

                    // Reposiciona o inimigo para fora da tela para que ele "volte"
                    enemy.x = (0..width).random()
                    enemy.y = -enemy.bitmap.height

                    player.killCount++

                    break
                }
            }
        }

        // Remove as balas que colidiram
        bullets.removeAll(bulletsToRemove)

        // Atualiza a posição dos inimigos restantes
        for (e in enemies) {
            e.update(player.speed)
            if (Rect.intersects(player.detectCollision, e.detectCollision)) {
                boomSoundPlayer.playSound()
                boom.x = e.x
                boom.y = e.y
                e.x = (0..width).random()
                e.y = -e.bitmap.height  // Reposiciona o inimigo fora da tela, acima
                player.health--
                if (player.health <= 0) {
                    playing = false
                }
            }
        }

        // Limita o número de balas e remove as que saíram da tela
        bullets = ArrayList(bullets.filter { !it.isOffScreen(width) })
    }


    fun draw() {
        if (surfaceHolder.surface.isValid) {
            canvas = surfaceHolder.lockCanvas()

            canvas.drawColor(Color.BLACK)

            canvas.drawBitmap(boom.bitmap, boom.x.toFloat(), boom.y.toFloat(), paint)

            paint.color = Color.WHITE
            for (star in stars) {
                paint.strokeWidth = star.starWidth.toFloat()
                canvas.drawPoint(star.x.toFloat(), star.y.toFloat(), paint)
            }

            canvas.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)

            for (e in enemies) {
                canvas.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
            }

            for (bullet in bullets) {
                canvas.drawBitmap(bullet.bitmap, bullet.x.toFloat(), bullet.y.toFloat(), paint)
            }


            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control() {
        Thread.sleep(17)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            val screenWidth = width

            when (it.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    if (it.x < screenWidth / 2) {
                        // Lado esquerdo da tela - Movimento do jogador
                        val touchY = it.y
                        player.updatePosition(touchY)
                        player.boosting = true
                    } else {
                        // Lado direito da tela - Tenta disparar uma bala com um atraso de meio segundo
                        val currentTime = System.currentTimeMillis()
                        if (currentTime - lastBulletTime >= 500) { // Verifica se passaram 500ms
                            val bullet = player.shootBullet(context)
                            bullets.add(bullet)
                            bulletSoundPlayer.playBulletSound()
                            lastBulletTime = currentTime  // Atualiza o tempo do último disparo
                        }
                    }
                }

                MotionEvent.ACTION_UP -> {
                    if (it.x < screenWidth / 2) {
                        player.boosting = false
                    } else {
                        player.boosting = false
                    }
                }
            }
            return true
        }
        return super.onTouchEvent(event)
    }
}



