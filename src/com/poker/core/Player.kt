package com.poker.core

class Player {
    private val name: String = ""
    private val chip: Int = 0
    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards += card
    }
}