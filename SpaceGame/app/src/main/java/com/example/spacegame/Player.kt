package com.example.spacegame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect


class Player(context: Context, width: Int, height: Int) {
    var x = 0
    var y = 0
    var speed = 0
    var max_x = 0
    var max_y = 0
    var min_x = 0
    var min_y = 0

    var bitmap: Bitmap
    var boosting = false

    private val MAX_SPEED = 20
    private val MIN_SPEED = 1

    var detectCollision = Rect()

    var health = 5
    var killCount = 0

    init {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)

        max_x = width
        min_x = 0

        max_y = height - bitmap.height
        min_y = 0

        x = 75
        y = 50

        speed = 1

        detectCollision = Rect(x, y, x + bitmap.width, y + bitmap.height)
    }

    fun update() {
        if (boosting) speed += 2
        else speed -= 5
        if (speed > MAX_SPEED) speed = MAX_SPEED
        if (speed < MIN_SPEED) speed = MIN_SPEED

        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }

    fun updatePosition(newY: Float) {
        y = newY.toInt().coerceIn(min_y, max_y)
        update()
    }

    fun shootBullet(context: Context): Bullet {
        // Cria uma nova instância de Bullet na posição do jogador
        return Bullet(context, x + bitmap.width, y + bitmap.height / 2)
    }

}
