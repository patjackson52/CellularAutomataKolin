package com.jackson.cellular_automata

import com.jackson.celluar_generator.CellularGenerator
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * JVM CLI implementation of CellularGenerator using characters for output.
 * Attempts to get the console width automatically and then print 1000 generations.
 * Takes the ruleset as a parameter
 */
class JvmCellularGenerator {
    companion object {

        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val ruleSet = args.first()
            val consoleWidth = getConsoleWidth()
            val generator = CellularGenerator(ruleSet.toInt(), consoleWidth)

            (0..1000).forEach {
                printGeneration(generator.next())
            }
        }

        fun printGeneration(generation: List<Boolean>) {
            val row = generation.map { if (it) '█' else ' ' }.joinToString("")
            println(row)
        }

        private fun getConsoleWidth(): Int {
            val rt = Runtime.getRuntime()
            val commands = arrayOf("tput", "cols")
            val proc = rt.exec(commands)
            val stdInput = BufferedReader(InputStreamReader(proc.inputStream))
            val numLines = stdInput.readLine().toInt()
            return numLines
        }
    }
}