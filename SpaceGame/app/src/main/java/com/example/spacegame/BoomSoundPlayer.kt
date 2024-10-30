package com.example.spacegame

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

class BoomSoundPlayer(context: Context) {
    private val soundPool: SoundPool
    private val soundId: Int

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(5) // Define o número máximo de streams simultâneos
            .setAudioAttributes(audioAttributes)
            .build()

        // Carrega o som para o SoundPool
        soundId = soundPool.load(context, R.raw.boom_sound, 1)
    }

    fun playSound() {
        // Toca o som simultaneamente se chamarmos várias vezes
        soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
    }

    fun release() {
        soundPool.release()
    }
}
