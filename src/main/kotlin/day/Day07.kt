package day

import DayOfCode

class Day07(filename: String? = null) : DayOfCode(filename ?: "07.data") {
    override fun solveOne(): Any = openStream().use { stream ->
        val hands = stream.readLines().map { HandOfCards1(it) }.sorted()
        
        var index = 0
        hands.sumOf { 
            index += 1
            it.bet * index
        }
    }

    override fun solveTwo(): Any = openStream().use { stream ->
        val hands = stream.readLines().map { HandOfCards2(it) }.sorted()

        var index = 0
        hands.sumOf {
            index += 1
            it.bet * index
        }
    }
    
    data class HandOfCards1(
        private val input: String
    ) : Comparable<HandOfCards1> {
        
        val cardHand: String = input.split(" ")[0]
        val bet: Long = input.split(" ")[1].toLong()
        val type: Type = run { 
            val counts = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            cardHand.forEach { 
                counts[it.cardValue() - 2] += 1
            }
            when {
                counts.any { it == 5 } -> Type.FiveOfAKind
                counts.any { it == 4 } -> Type.FourOfAKind
                counts.any { it == 3 } -> {
                    if (counts.any { it == 2 }) {
                        Type.FullHouse
                    } else {
                        Type.ThreeOfAKind
                    }
                }
                counts.any { it == 2 } -> {
                    if (counts.count { it == 2 } == 2) {
                        Type.TwoPair
                    } else {
                        Type.OnePair
                    }
                }
                else -> Type.HighCard
            }
        }
        
        private fun Char.cardValue(): Int = when (this) {
            'A' -> 14
            'K' -> 13
            'Q' -> 12
            'J' -> 11
            'T' -> 10
            else -> this.digitToInt()
        }
        
        override fun compareTo(other: HandOfCards1): Int = when  {
            this.type.ordinal < other.type.ordinal -> -1
            this.type.ordinal > other.type.ordinal -> 1
            else -> {
                var compare: Int? = null
                for (index in cardHand.indices) {
                    if (this.cardHand[index].cardValue() < other.cardHand[index].cardValue()) {
                        compare = -1
                        break
                    }
                    if (this.cardHand[index].cardValue() > other.cardHand[index].cardValue()) {
                        compare = 1
                        break
                    }
                }
                compare ?: 0
            }
        }
        
        enum class Type {
            HighCard, OnePair, TwoPair, ThreeOfAKind, FullHouse, FourOfAKind, FiveOfAKind
        }
    }

    data class HandOfCards2(
        private val input: String
    ) : Comparable<HandOfCards2> {

        val cardHand: String = input.split(" ")[0]
        val bet: Long = input.split(" ")[1].toLong()
        val counts: List<Int> = run {
            val c = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            cardHand.forEach {
                c[it.cardValue() - 1] += 1
            }
            c
        }
        val type: Type = run {
            val noJ = counts.subList(1, counts.size)
            when (noJ.max() + counts[0]) {
                5 -> Type.FiveOfAKind
                4 -> Type.FourOfAKind
                3 -> {
                    if (noJ.any { it == 3 } && noJ.any { it ==2 }) {
                        Type.FullHouse
                    } else if (noJ.count { it == 2 } == 2 && counts[0] == 1) {
                        Type.FullHouse
                    } else {
                        Type.ThreeOfAKind
                    }
                }
                2 -> {
                    if (counts.count { it == 2 } == 2) {
                        Type.TwoPair
                    } else {
                        Type.OnePair
                    }
                }
                else -> Type.HighCard
            }
        }

        private fun Char.cardValue(): Int = when (this) {
            'A' -> 13
            'K' -> 12
            'Q' -> 11
            'J' -> 1
            'T' -> 10
            else -> this.digitToInt()
        }

        override fun compareTo(other: HandOfCards2): Int = when  {
            this.type.ordinal < other.type.ordinal -> -1
            this.type.ordinal > other.type.ordinal -> 1
            else -> {
                var compare: Int? = null
                for (index in cardHand.indices) {
                    if (this.cardHand[index].cardValue() < other.cardHand[index].cardValue()) {
                        compare = -1
                        break
                    }
                    if (this.cardHand[index].cardValue() > other.cardHand[index].cardValue()) {
                        compare = 1
                        break
                    }
                }
                compare ?: 0
            }
        }

        enum class Type {
            HighCard, OnePair, TwoPair, ThreeOfAKind, FullHouse, FourOfAKind, FiveOfAKind
        }
    }
}
