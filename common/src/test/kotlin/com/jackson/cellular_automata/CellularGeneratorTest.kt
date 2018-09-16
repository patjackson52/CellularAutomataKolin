package com.jackson.cellular_automata

import com.jackson.celluar_generator.CellularGenerator
import kotlin.test.Test
import kotlin.test.assertTrue

class CellularGeneratorTest {
    @Test
    fun shouldGetRule() {
        val generator = CellularGenerator(90, 300, object : CellularGenerator.Callbacks {
            override fun onNextGeneration(currentGeneration: List<Boolean>, genNum: Long): Boolean {
                printGeneration(currentGeneration)
                return genNum <= 1000
            }
        })

        generator.start()
    }

    fun printGeneration(generation: List<Boolean>) {
        val row = generation.map { if (it) '1' else '0' }.joinToString("")
        println(row)
    }

    val rulesMap2: Map<List<Boolean>, Int> = mapOf(
            listOfBoolean("111") to 0,
            listOfBoolean("101") to 1,
            listOfBoolean("101") to 2,
            listOfBoolean("000") to 3
    )

    @Test
    fun canUseBooleanArrayAsKey() {
        val key = listOfBoolean("000")

        val result = rulesMap2[key]!!
        println("value = $result")
        assertTrue { result == 3 }
    }

}
