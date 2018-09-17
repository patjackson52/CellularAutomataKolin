package com.jackson.cellular_automata


/**
 * @return BooleanArray that matches the binary representation of the Int.
 *          i.e. 1.toBooleanArray    --->  [false,false,false,false,false,false,false,true]
 */
fun Int.toBooleanArray(): BooleanArray =
        (7 downTo 0).map { this.shr(it).and(1) == 1 }.toBooleanArray()

/**
 * @param binaryNum String containing binary number
 * @return Boolean array matching binaryNum
 * @throws IllegalArgumentException if binaryNum contains chars other than '1' or '0'
 */
fun booleanArrayOf(binaryNum: String) =
        binaryNum.map { it }.toCharArray().map { if (it == '0') false else if (it == '1') true else throw IllegalArgumentException("Arguments must be 1 or 0") }.toBooleanArray()


/**
 * @return CharArray with binary representation of BooleanArray
 */
fun BooleanArray.toCharArray(): CharArray {
    return map { if (it) '1' else '0' }.toCharArray()
}

/**
 * @return String with binary representation of BooleanArray
 */
fun BooleanArray.toBinaryString(): String = toCharArray().joinToString("")
