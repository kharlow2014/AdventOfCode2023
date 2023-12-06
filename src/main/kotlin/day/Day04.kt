package day

import DayOfCode

class Day04(filename: String? = null) : DayOfCode(filename ?: "04.data") {

    override fun solveOne(): Any = openStream().use { stream ->
        stream.readLines().sumOf { line ->
            LottoGame(line).points
        }
    }

    override fun solveTwo(): Any = openStream().use { stream ->
        val games = stream.readLines().map { LottoGame(it) }.associateBy { it.gameId }
        val cardsWonForGameId: MutableMap<Int, Int> = mutableMapOf()
        games.toSortedMap().toList().asReversed().forEach { (gameId, game) ->
            cardsWonForGameId[gameId] = game.cardsCopied.sumOf { cardsWonForGameId[it] ?: 0 } + 1
        }

        return cardsWonForGameId.toList().sumOf { it.second }
    }

    data class LottoGame(val data: String) {
        private val splitData = data.split(":", "|").map { it.trim() }
        val gameId = splitData[0].split(" ").last().toInt()
        private val winningNumbers = splitData[1].split("\\s+".toRegex()).map { it.toInt() }
        private val myNumbers = splitData[2].split("\\s+".toRegex()).map { it.toInt() }
        private val matchCount: Int = myNumbers.sumOf {
            if (winningNumbers.contains(it)) {
                POINTS_PER_WIN
            } else {
                0
            }
        }
        val cardsCopied = (1..matchCount).map { gameId + it }

        val points: Int
            get() = if (matchCount == 0) 0 else 2.power(matchCount - 1)

        private fun Int.power(power: Int): Int = if (power == 0) {
            1
        } else {
            this * this.power(power - 1)
        }

        companion object {
            private const val POINTS_PER_WIN: Int = 1
        }
    }
}
