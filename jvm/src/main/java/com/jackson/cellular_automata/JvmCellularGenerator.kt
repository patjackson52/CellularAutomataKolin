package com.jackson.cellular_automata

import com.jackson.celluar_generator.CellularGenerator
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class JvmCellularGenerator {
    companion object {

        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val ruleSet = args.first()
            val consoleWidth = getConsoleWidth()
            val generator = CellularGenerator(ruleSet.toInt(), consoleWidth, object : CellularGenerator.Callbacks {
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