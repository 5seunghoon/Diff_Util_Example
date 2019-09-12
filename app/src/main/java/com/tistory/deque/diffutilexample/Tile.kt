package com.tistory.deque.diffutilexample

import android.graphics.Color
import kotlin.random.Random

data class Tile(val number: Int) {
    val color: Int =
        if (number < 100) Color.BLACK + (256 * 256 * 256 * number * (100 - number) / 25) / (100)
        else Color.WHITE


    override fun equals(other: Any?): Boolean {
        (other as? Tile)?.let {
            return this.number == it.number
        } ?: return super.equals(other)
    }
}