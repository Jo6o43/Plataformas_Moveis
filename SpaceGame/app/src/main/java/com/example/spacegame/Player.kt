package com.example.spacegame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

import java.util.Random


class Player {
    var x = 0
    var y = 0
    var speed = 0
    var max_x = 0
    var max_y = 0
    var min_x = 0
    var min_y = 0
    val generator = Random()

    var bitmap: Bitmap

    var boosting = false

    private val GRAVITY = -10
    private val MAX_SPEED = 20
    private val MIN_SPEED = 1

    var detectCollision = Rect()


    constructor(context: Context, width: Int, height: Int) {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)

        max_x = width
        min_x = 0

        max_y = height - bitmap!!.height
        min_y = 0

        x = 75
        y = 50

        speed = 1

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update() {
        if (boosting) {
            speed += 2
        } else {
            speed -= 5
        }
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED
        }

        y -= speed + GRAVITY

        if (y < min_y) {
            y = min_y
        }
        if (y > max_y) {
            y = max_y
        }

        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }
}