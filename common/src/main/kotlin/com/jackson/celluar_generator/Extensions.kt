package com.jackson.cellular_automata


fun Int.toBooleanArray(): BooleanArray =
        (7 downTo 0).map { this.shr(it).and(1) == 1 }.toBooleanArray()

fun booleanArrayOf(vararg ints: Int): BooleanArray =
        ints.map { if (it == 0) false else if (it == 1) true else throw IllegalArgumentException("Arguments must be 1 or 0") }.toBooleanArray()