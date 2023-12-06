package day

import DayOfCode

class Day02(filename: String? = null) : DayOfCode(filename ?: "02.data") {
    
    override fun solveOne() = openStream().use { stream ->
        stream.readLines().sumOf { line ->
            val gameId = line.split(":")[0].split(" ").last().toInt()
            var isValid = true
            line.split(":").drop(1).forEach { games -> 
                games.split(";").forEach { game ->
                    game.split(",").forEach { draws ->
                        draws.trim().split(" ").run { 
                            when (this[1]) {
                                "red" -> if (this[0].toInt() > 12) isValid = false
                                "green" -> if (this[0].toInt() > 13) isValid = false
                                "blue" -> if (this[0].toInt() > 14) isValid = false
                            }
                        }
                    }
                }
            }
            if (isValid) gameId else 0
        }
    }

    override fun solveTwo() =  openStream().use { stream ->
        stream.readLines().sumOf { line ->
            val bagOfCubes = BagOfCubes()
            line.split(":").drop(1).forEach { games ->
                games.split(";").forEach { game ->
                    game.split(",").forEach { draws ->
                        draws.trim().split(" ").run { 
                            when (this[1]) {
                                "red" -> if (this[0].toInt() > bagOfCubes.red) bagOfCubes.red = this[0].toInt()
                                "green" -> if (this[0].toInt() > bagOfCubes.green) bagOfCubes.green = this[0].toInt()
                                "blue" -> if (this[0].toInt() > bagOfCubes.blue) bagOfCubes.blue = this[0].toInt()
                            }
                        }
                    }
                }
            }
            bagOfCubes.power
        }
    }
    
    data class BagOfCubes(
        var red: Int = 0,
        var green: Int = 0,
        var blue: Int = 0
    ) {
        val power: Long
            get() = red.toLong() * green * blue
    }
}
