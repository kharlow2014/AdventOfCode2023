package day

import DayOfCode

class Day06(filename: String? = null) : DayOfCode(filename ?: "06.data") {
    override fun solveOne(): Any = openStream().use { stream ->
        val data = stream.readLines()
        val times = data[0].split(": ")[1].trim().split("\\s+".toRegex()).map { it.toLong() }
        val distance = data[1].split(": ")[1].trim().split("\\s+".toRegex()).map { it.toLong() }
        
        val holdTimes = times.indices.map { index ->
            var holdTime = HoldTime(min = 0, max = times[index])
            while (holdTime.min * (times[index] - holdTime.min) <= distance[index]) {
                holdTime = holdTime.copy(min = holdTime.min + 1)
            }
            while (holdTime.max * (times[index] - holdTime.max) <= distance[index]) {
                holdTime = holdTime.copy(max = holdTime.max - 1)
            }
            
            holdTime
        }
        
        return holdTimes.productOf { it.max - it.min + 1}
    }

    override fun solveTwo(): Any = openStream().use { stream ->
        val data = stream.readLines()
        
        val time = data[0].split(": ")[1].trim().split("\\s+".toRegex()).joinToString("").toLong()
        val distance = data[1].split(": ")[1].trim().split("\\s+".toRegex()).joinToString("").toLong()
        
        var holdTime = HoldTime(min = 0, max = time)
        while (holdTime.min * (time - holdTime.min) <= distance) {
            holdTime = holdTime.copy(min = holdTime.min + 1)
        }
        while (holdTime.max * (time - holdTime.max) <= distance) {
            holdTime = holdTime.copy(max = holdTime.max - 1)
        }
        
        holdTime.max - holdTime.min + 1
    }
    
    private inline fun <T> Iterable<T>.productOf(selector: (T) -> Long): Long {
        var product = 1L
        this.forEach { product *= selector(it) }
        return product
    }
    
    data class HoldTime(
        val min: Long = 0,
        val max: Long = 0
    )
}
