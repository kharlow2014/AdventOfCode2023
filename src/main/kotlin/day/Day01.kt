package day

import DayOfCode

class Day01(filename: String?) : DayOfCode(filename) {

    override fun solveOne(filename: String?): Any {
        return openStream(filename).readLines().sumOf { line ->
            "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
        }
    }

    override fun solveTwo(filename: String?): Any {
        return openStream(filename).readLines().sumOf { line ->
            var firstNumAndIndex: Pair<Int, Int> = 0 to Int.MAX_VALUE
            var lastNumAndIndex: Pair<Int, Int> = 0 to Int.MIN_VALUE
            for (num in 1..9) {
                val firstIndexOfInt = line.indexOf(num.toString())
                if (firstIndexOfInt >= 0 && firstIndexOfInt < firstNumAndIndex.second) {
                    firstNumAndIndex = num to firstIndexOfInt
                }
                
                val firstIndexOfWord = line.indexOf(num.toWord())
                if (firstIndexOfWord >= 0 && firstIndexOfWord < firstNumAndIndex.second) {
                    firstNumAndIndex = num to firstIndexOfWord
                }
                
                val lastIndexOfInt = line.lastIndexOf(num.toString())
                if (lastIndexOfInt >= 0 && lastIndexOfInt > lastNumAndIndex.second) {
                    lastNumAndIndex = num to lastIndexOfInt
                }
                
                val lastIndexOfWord = line.lastIndexOf(num.toWord())
                if (lastIndexOfWord >= 0 && lastIndexOfWord > lastNumAndIndex.second) {
                    lastNumAndIndex = num to lastIndexOfWord
                }
            }
            "${firstNumAndIndex.first}${lastNumAndIndex.first}".toInt()
        }
    }
    
    private fun Int.toWord() = when (this) {
        1 -> "one"
        2 -> "two"
        3 -> "three"
        4 -> "four"
        5 -> "five"
        6 -> "six"
        7 -> "seven"
        8 -> "eight"
        9 -> "nine"
        else -> throw UnsupportedOperationException()
    }
}