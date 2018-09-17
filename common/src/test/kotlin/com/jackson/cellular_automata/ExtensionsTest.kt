package com.jackson.cellular_automata

import kotlin.test.Test
import kotlin.test.assertTrue


class ExtensionsTest {

    @Test
    fun getBooleanArrayFor1() {
        val booleanArray = 1.toBooleanArray()

        val expectedArray = booleanArrayOf("00000001")

        printBooleanArray(booleanArray, "result")
        printBooleanArray(expectedArray, "expected")
        assertTrue { booleanArray.contentEquals(expectedArray) }
    }

    @Test
    fun getBooleanArrayFor2() {
        val booleanArray = 2.toBooleanArray()
        val expectedArray = booleanArrayOf("00000010")

        assertTrue { booleanArray.contentEquals(expectedArray) }
    }

    @Test
    fun getBooleanArrayFor30() {
        val booleanArray = 30.toBooleanArray()

        val expectedArray = booleanArrayOf("00011110")

        assertTrue { booleanArray.contentEquals(expectedArray) }
    }

    private fun printBooleanArray(ary: BooleanArray, label: String) {
        println(label)
        ary.forEach { print("$it ") }
        println()
    }

}