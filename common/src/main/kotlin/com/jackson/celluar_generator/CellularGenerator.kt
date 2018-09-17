package com.jackson.celluar_generator

import com.jackson.cellular_automata.toBinaryString
import com.jackson.cellular_automata.toBooleanArray

/**
 * Generates new generations of 1 dimension Cellular Automata
 * according to Wolfram's classification scheme (https://en.m.wikipedia.org/wiki/Cellular_automaton#Classification)
 *
 * New generations can be generated by calling next().  Class only maintains the current generation, not the history.
 *
 * @param ruleSet 1 - 256 Wolfram classification scheme
 * @param width width which will be used for the output
 */
class CellularGenerator(ruleSet: Int, private val width: Int) {

    companion object {

        /**
         * All possible patterns for a positions 'neighborhood'
         * ORDER IS IMPORTANT.
         */
        private val rulesKeysArray = arrayOf(
                "111",
                "110",
                "101",
                "100",
                "011",
                "010",
                "001",
                "000"
        )
    }

    /**
     * After init(), it will contain ruleset to look up the next generation of a given position
     * by using its neighborhood.
     */
    private val rulesMap: Map<String, Boolean>

    private var currentGeneration: BooleanArray = booleanArrayOf()

    init {
        val rule = ruleSet.toBooleanArray()
        rulesMap = (0..7).associate { rulesKeysArray[it] to rule[it] }
    }

    /**
     * @return the next generation using the given ruleset.
     */
    fun next(): BooleanArray {
        currentGeneration = if (currentGeneration.isEmpty()) {
            createFirstGen()
        } else {
            generateNext(currentGeneration)
        }
        return currentGeneration
    }

    /**
     * Creates the first generation, with a single true value in the center of the array.
     */
    private fun createFirstGen(): BooleanArray {
        val firstGenerationArray = (0 until width).map { false }.toBooleanArray()
        firstGenerationArray[width / 2] = true
        return firstGenerationArray
    }

    private fun generateNext(current: BooleanArray): BooleanArray {
        return (0 until width).map {
            val neighborHood = when (it) {
                0 -> "0" + current.sliceArray(it..it + 1).toBinaryString()
                width - 1 -> current.sliceArray(it - 1..it).toBinaryString() + "0"
                else -> current.sliceArray(it - 1..it + 1).toBinaryString()
            }
            rulesMap[neighborHood]!!
        }.toBooleanArray()
    }

}
