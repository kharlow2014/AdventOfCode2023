package day

import DayOfCode

class Day08(filename: String? = null) : DayOfCode(filename ?: "08.data") {

    override fun solveOne(): Any = openStream().use { stream ->
        Instructions(stream.readLines()).stepsToEnd()
    }

    override fun solveTwo(): Any = openStream().use { stream ->
        Instructions(stream.readLines()).spookyStepsToEnd()
    }

    data class Instructions(
        private val input: List<String>
    ) {
        private val dirs = input[0]
        private val connections = input.subList(2, input.size).map { line ->
            val split = regex.findAll(line)
            Connection(
                start = split.elementAt(0).value,
                left = split.elementAt(1).value,
                right = split.elementAt(2).value
            )
        }

        fun stepsToEnd(): Int {
            return loopSize(connections.first { it.start == "AAA" }, "ZZZ")
        }

        fun spookyStepsToEnd(): Long {
            val startingNodes = connections.filter { it.start.endsWith('A') }.toMutableList()
            val loopSizes = startingNodes.map { loopSize(it, "Z").toLong() }


            return lcm(loopSizes)
        }

        private fun loopSize(startingNode: Connection, endNodeEndsWith: String): Int {
            var dirIndex = 0
            var stepCount = 0

            var currentNode = startingNode
            while (!currentNode.start.endsWith(endNodeEndsWith)) {
                currentNode = if (dirs[dirIndex] == 'L') {
                    connections.first { it.start == currentNode.left }
                } else {
                    connections.first { it.start == currentNode.right }
                }
                dirIndex = if (dirIndex == dirs.length - 1) 0 else dirIndex + 1
                stepCount++
            }
            return stepCount
        }

        private fun gcd(a: Long, b: Long): Long = if (b == 0L) {
            a
        } else {
            gcd(b, a % b)
        }

        private fun lcm(data: List<Long>): Long {
            var lcm = data[0]
            data.forEach { 
                lcm = (lcm * it) / gcd(lcm, it)
            }
            return lcm
        }

        data class Connection(
            val start: String,
            val left: String,
            val right: String
        )

        companion object {
            private val regex = "([A-Z0-9])\\w+".toRegex()
        }
    }
}
