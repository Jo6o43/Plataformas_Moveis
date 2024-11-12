package com.example.spacegame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Bullet(context: Context, startX: Int, startY: Int) {
    var x = startX
    var y = startY
    var speed = 20

    var detectCollision = Rect()
    var bitmap: Bitmap

    init {

        val originalBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.bullet)
        val bulletWidth = originalBitmap.width / 4
        val bulletHeight = originalBitmap.height / 4
        bitmap = Bitmap.createScaledBitmap(originalBitmap, bulletWidth, bulletHeight, false)
    }

    fun update() {
        x += speed


        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }

    fun isOffScreen(screenWidth: Int): Boolean {
        return x > screenWidth
    }
}

