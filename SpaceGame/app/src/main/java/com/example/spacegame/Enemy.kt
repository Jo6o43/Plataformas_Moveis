package com.example.spacegame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.util.Random

class Enemy {

    var x = 0
    var y = 0
    var speed = 0
    var max_x = 0
    var max_y = 0
    var min_x = 0
    var min_y = 0
    val generator = Random()

    var bitmap: Bitmap

    var detectCollision = Rect()


    constructor(context: Context, width: Int, height: Int) {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy)

        max_x = width
        min_x = 0

        max_y = height - bitmap!!.height
        min_y = 0

        x = max_x
        y = generator.nextInt(max_y)

        speed = generator.nextInt(6) + 10

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(playerSpeed: Int) {
        x -= playerSpeed
        x -= speed

        if (x < min_x-bitmap.width) {
            x = max_x
            y = Random().nextInt(max_y)

            speed = generator.nextInt(6) + 16
        }

        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }
}
