package day

import DayOfCode
import kotlin.math.min

class Day01(filename: String) : DayOfCode(filename) {

    override fun solveOne(): Any {
        return openStream().readLines().sumOf { line ->
            CalibrationInstructions(line).digitsOnly
        }
    }

    override fun solveTwo(): Any {
        return openStream().readLines().sumOf { line ->
            CalibrationInstructions(line).digitsOrWords
        }
    }
    
    data class CalibrationInstructions(val data: String) {
        val digitsOnly: Int
            get() = "${data.first { it.isDigit() }}${data.last { it.isDigit() }}".toInt()
        
        val digitsOrWords: Int
            get() = run {
                var numbers = Numbers()
                for (num in 1..9) {
                    data.indexOfOrNull(num)?.let { 
                        if (it < numbers.firstNumIndex) {
                            numbers = numbers.copy(firstNum = num, firstNumIndex = it)
                        }
                    }
                    
                    data.indexOfOrNull(num)?.let { 
                        if (it > numbers.lastNumIndex) {
                            numbers = numbers.copy(lastNum = num, lastNumIndex = it)
                        }
                    }
                }
                numbers.combined
            }
        
        private fun String.indexOfOrNull(num: Int): Int? {
            val indexOfInt = this.indexOf(num.toString()).nullIfNegative()
            val indexOfWord = this.indexOf(num.toWord()).nullIfNegative()
            return when {
                indexOfInt == null && indexOfWord != null -> indexOfWord
                indexOfInt != null && indexOfWord == null -> indexOfInt
                indexOfInt != null && indexOfWord != null -> min(indexOfInt, indexOfWord)
                else -> null
            }
        }
        
        private fun Int.nullIfNegative() = if (this < 0) null else this
        
        data class Numbers(
            val firstNum: Int = 0,
            val firstNumIndex: Int = Int.MAX_VALUE,
            val lastNum: Int = 0,
            val lastNumIndex: Int = Int.MIN_VALUE
        ) {
            val combined = "$firstNum$lastNum".toInt()
        }
        
        companion object {
            private val numToWordMap = mapOf(
                1 to "one",
                2 to "two",
                3 to "three",
                4 to "four",
                5 to "five",
                6 to "six",
                7 to "seven",
                8 to "eight",
                9 to "nine"
            )
            
            private fun Int.toWord() = numToWordMap[this] ?: throw UnsupportedOperationException()
        }

    }
}