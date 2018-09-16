package com.jackson.celluar_generator

import com.jackson.cellular_automata.listOfBoolean
import com.jackson.cellular_automata.toBooleanArray

/**
 * Generates new generations of 1 dimension Cellular Automata
 * according to Wolfram's classification scheme (https://en.m.wikipedia.org/wiki/Cellular_automaton#Classification)
 *
 * New generations can be generated by calling next().
 *
 * @param ruleSet 1 - 256 Wolfram classification scheme
 * @param width width which will be used for the output
 */
class CellularGenerator(ruleSet: Int, private val width: Int) {

    private val rulesMap: Map<List<Boolean>, Boolean>
    private val rulesKeysArray = arrayOf(
            listOfBoolean("111"),
            listOfBoolean("110"),
            listOfBoolean("101"),
            listOfBoolean("100"),
            listOfBoolean("011"),
            listOfBoolean("010"),
            listOfBoolean("001"),
            listOfBoolean("000")
    )
    private var currentGeneration: List<Boolean> = listOf()

    init {
        val rule = ruleSet.toBooleanArray()
        rulesMap = (0..7).associate { rulesKeysArray[it] to rule[it] }
    }

    /**
     * @return the next generation using the given ruleset.  The first call will return
     * the first generation, with a single true value in the center of the array.
     */
    fun next(): List<Boolean> {
        if (currentGeneration.isEmpty()) {
            val firstGenerationArray = (0 until width).map { '0' }.toCharArray()
            firstGenerationArray[width / 2] = '1'
            currentGeneration = listOfBoolean(firstGenerationArray.joinToString(""))
        } else {
            currentGeneration = generateNext(currentGeneration)
        }
        return currentGeneration
    }

    private fun generateNext(current: List<Boolean>): List<Boolean> {
        return (0 until width).map {
            val neighborHood = when (it) {
                0 -> listOf(false).plus(current.subList(it, it + 2))
                width - 1 -> current.subList(it - 1, it + 1).plus(false)
                else -> current.subList(it - 1, it + 2)
            }
            rulesMap[neighborHood]!!
        }.toList()
    }
}
