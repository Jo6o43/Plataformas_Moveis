package com.example.spacegame

import java.util.Random


class Star {
    var x = 0
    var y = 0
    var speed = 0
    var max_x = 0
    var max_y = 0
    var min_x = 0
    var min_y = 0
    val generator = Random()

    constructor(width: Int, height: Int) {
        max_x = width
        max_y = height
        min_x = 0
        min_y = 0

        x = generator.nextInt(max_x)
        y = generator.nextInt(max_y)

        speed = generator.nextInt(15) + 1
    }

    fun update(playerSpeed: Int) {
        x -= playerSpeed
        x -= speed

        if (x < 0) {
            x = max_x
            y = Random().nextInt(max_y)

            speed = generator.nextInt(15) + 1
        }
    }

    var starWidth: Int = 0
    get(){
        return generator.nextInt(10) + 1
    }
}