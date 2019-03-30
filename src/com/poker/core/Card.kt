package com.poker.core

enum class Suit {
    CLUB, DIAMOND, HEART, SPADE
}

data class Card(
    val suit: Suit,
    val number: Int
) : Comparable<Card> {
    init {
        require(number in 1..13) { "number must be between 1 and 13" }
    }

    private fun getNumberString(): String =
        when (number) {
            1 -> "A"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> number.toString()
        }

    override operator fun compareTo(other: Card): Int =
        when {
            number == 1 && other.number != 1 -> 1
            number != 1 && other.number == 1 -> -1
            number < other.number -> -1
            number > other.number -> 1
            else -> 0
        }

    override fun toString(): String {
        return "$suit ${getNumberString()}"
    }
}