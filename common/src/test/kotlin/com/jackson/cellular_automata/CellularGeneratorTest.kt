package com.jackson.cellular_automata

import com.jackson.celluar_generator.CellularGenerator
import kotlin.test.Test
import kotlin.test.assertTrue

class CellularGeneratorTest {
    @Test
    fun shouldGetRule() {
        val generator = CellularGenerator(90, 10)
        (0..10).forEach {
            val nextGen = generator.next()
            assertTrue(nextGen == firstTenRule30Generations[it])
        }
    }

    private fun printGeneration(generation: List<Boolean>) {
        val row = generation.map { if (it) '1' else '0' }.joinToString("")
        println(row)
    }


    @Test
    fun canUseBooleanArrayAsKey() {
        val testRulesMap: Map<List<Boolean>, Int> = mapOf(
                listOfBoolean("111") to 0,
                listOfBoolean("101") to 1,
                listOfBoolean("101") to 2,
                listOfBoolean("000") to 3
        )
        val key = listOfBoolean("000")

        val result = testRulesMap[key]!!
        println("value = $result")
        assertTrue { result == 3 }
    }

    private val firstTenRule30Generations = listOf(
            listOfBoolean("0000010000"),
            listOfBoolean("0000101000"),
            listOfBoolean("0001000100"),
            listOfBoolean("0010101010"),
            listOfBoolean("0100000001"),
            listOfBoolean("1010000010"),
            listOfBoolean("0001000101"),
            listOfBoolean("0010101000"),
            listOfBoolean("0100000100"),
            listOfBoolean("1010001010"),
            listOfBoolean("0001010001")
    )
}
