package com.poker.core

class Deck {
    private var deck: MutableList<Card> = mutableListOf()

    init {
        initialize()
    }

    fun initialize() {
        deck.clear()
        Suit.values().forEach { suit ->
            (1..13).forEach { number ->
                deck.add(Card(suit, number))
            }
        }
        deck.shuffle()
    }

    fun draw(): Card = deck.removeAt(deck.size - 1)
}