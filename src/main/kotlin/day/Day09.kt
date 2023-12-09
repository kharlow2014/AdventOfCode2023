package day

import DayOfCode

class Day09(filename: String? = null) : DayOfCode(filename ?: "09.data") {
    
    override fun solveOne(): Any = openStream().use { stream ->
        stream.readLines().map { line -> Pattern(line.split(" ").map { it.toInt() }) }.sumOf { it.findNextValue().toLong() }
    }

    override fun solveTwo(): Any = openStream().use { stream ->
        stream.readLines().map { line -> Pattern(line.split(" ").map { it.toInt() }) }.sumOf { it.findLastValue().toLong() }
    }

    data class Pattern(val data: List<Int>) {
        fun findNextValue(): Int {
            return calculateNextValue(data)
        }
        
        fun findLastValue(): Int  {
            return calculateLastValue(data)
        }
        
        private fun calculateNextValue(data: List<Int>): Int {
            val next = getNextLevel(data)
            if (next.all { it == 0 }) {
                return data.last()
            }
            
            return calculateNextValue(next) + data.last()
        }
        
        private fun calculateLastValue(data: List<Int>): Int {
            val next = getNextLevel(data)
            if (next.all { it == 0 }) {
                return data.first()
            }
            
            return data.first() - calculateLastValue(next)
        }
        
        private fun getNextLevel(data: List<Int>): List<Int> {
            val next = Array(data.size - 1) { 0 }
            for (i in next.indices) {
                next[i] = data[i + 1] - data[i]
            }
            return next.toList()
        }
    }
}
