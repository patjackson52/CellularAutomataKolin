package com.jackson.cellular_automata

import com.jackson.celluar_generator.CellularGenerator
import kotlin.test.Test
import kotlin.test.assertTrue

class CellularGeneratorTest {

    @Test
    fun verifyFirst10Generations() {
        val generator = CellularGenerator(90, 10)
        (0..10).forEach {
            val nextGen = generator.next()
            assertTrue(nextGen.contentEquals(firstTenRule30Generations[it]))
        }
    }

    private fun printGeneration(generation: List<Boolean>) {
        val row = generation.map { if (it) '1' else '0' }.joinToString("")
        println(row)
    }


    private val firstTenRule30Generations = listOf(
            booleanArrayOf("0000010000"),
            booleanArrayOf("0000101000"),
            booleanArrayOf("0001000100"),
            booleanArrayOf("0010101010"),
            booleanArrayOf("0100000001"),
            booleanArrayOf("1010000010"),
            booleanArrayOf("0001000101"),
            booleanArrayOf("0010101000"),
            booleanArrayOf("0100000100"),
            booleanArrayOf("1010001010"),
            booleanArrayOf("0001010001")
    )
}
