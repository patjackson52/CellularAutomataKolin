package com.jackson.cellular_automata

import kotlin.test.Test
import kotlin.test.assertTrue


class ExtensionsTest {

    @Test
    fun getBooleanArrayFor1() {
        val booleanArray = 1.toBooleanArray()

        val expectedArray = booleanArrayOf(false, false, false, false, false, false, false, true)

        printBooleanArray(booleanArray, "result")
        printBooleanArray(expectedArray, "expected")
        assertTrue { booleanArray.contentEquals(expectedArray) }
    }

    @Test
    fun getBooleanArrayFor2() {
        val booleanArray = 2.toBooleanArray()

        val expectedArray = booleanArrayOf(false, false, false, false, false, false, true, false)

        assertTrue { booleanArray.contentEquals(expectedArray) }
    }

    @Test
    fun getBooleanArrayFor30() {
        val booleanArray = 30.toBooleanArray()

        val expectedArray = booleanArrayOf(0, 0, 0, 1, 1, 1, 1, 0)

        assertTrue { booleanArray.contentEquals(expectedArray) }
    }

    fun printBooleanArray(ary: BooleanArray, label: String) {
        println(label)
        ary.forEach { print("$it ") }
        println()
    }

}