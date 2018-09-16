package com.jackson.celluar_generator

import com.jackson.cellular_automata.listOfBoolean
import com.jackson.cellular_automata.toBooleanArray


class CellularGenerator(ruleSet: Int, val width: Int) {

    val rulesMap: Map<List<Boolean>, Boolean>
    val rulesKeysArray = arrayOf(
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


/*
val rulesMap: Map<BitSet, Boolean>
val rulesArray = arrayOf(
        bitSetOf(true, true, true),
        bitSetOf(true, true, false),
        bitSetOf(true, false, true),
        bitSetOf(true, false, false),
        bitSetOf(false, true, true),
        bitSetOf(false, true, false),
        bitSetOf(false, false, true),
        bitSetOf(false, false, false)
)

init {
    val rule = BitSet.valueOf(ruleSet)
    val booleanArray = BooleanArray(1)
    booleanArrayOf()
    rulesMap = (0..7).associate { rulesArray[it] to }
}

fun start() {
    val firstGeneration = BitSet(width)
    firstGeneration.set(width / 2)
}

interface Callbacks {
    fun onNextGeneration()
}

private fun generateNext(current: BitSet) {
    val nextGen = BitSet(width)
    for (i in 1..width) {
        val neighborHood = current.get(i - 1, i + 1)
    }
}

private fun findNextGen(neighborHood: BitSet) {

    bitSetOf(true, false, true)

}

fun bitSetOf(vararg bits: Boolean): BitSet {
    val bitSet = BitSet(bits.size)
    bits.forEachIndexed { index, b -> bitSet.set(index, b) }
    return bitSet
}
*/
