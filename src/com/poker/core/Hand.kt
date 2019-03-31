package com.poker.core

class Hand(
    val rank: Rank,
    private val mainCards: List<Card>,
    private val subCards: List<Card> = listOf(),
    private val kickers: List<Card> = listOf()
) {
    enum class Rank {
        NONE,
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH
    }

    val cards: List<Card>
        get() = mainCards + subCards + kickers

    operator fun compareTo(other: Hand): Int {
        val rankComp = rank.compareTo(other.rank)
        if (rankComp != 0) {
            return rankComp
        }

        for (i in 0 until mainCards.size) {
            val cardComp = mainCards[i].compareTo(other.mainCards[i])
            if (cardComp != 0) {
                return cardComp
            }
        }

        for (i in 0 until subCards.size) {
            val cardComp = subCards[i].compareTo(other.subCards[i])
            if (cardComp != 0) {
                return cardComp
            }
        }

        for (i in 0 until kickers.size) {
            val cardComp = kickers[i].compareTo(other.kickers[i])
            if (cardComp != 0) {
                return cardComp
            }
        }
        return 0
    }

    override fun toString(): String = "$rank (${cards.joinToString(", ")})"

    companion object {
        fun parse(cards: List<Card>): Hand {
            val sortedCards = cards.sortedDescending()
            val flush = cards.all { it.suit == cards.first().suit }
            val straight = isStraight(sortedCards)

            @Suppress("UNCHECKED_CAST")
            val groupedCards =
                (sortedCards.groupingBy { it.number }.aggregate { _, acc: MutableSet<Card>?, element, first ->
                    if (first) {
                        mutableSetOf(element)
                    } else {
                        acc!!.add(element)
                        acc
                    }
                } as Map<Int, MutableSet<Card>>).values.sortedWith(Comparator { a, b ->
                    (-a.size.compareTo(b.size)).takeIf { it != 0 }
                        ?: -a.first().compareTo(b.first())
                })

            return when {
                straight && flush -> {
                    if (!isRoyalStraight(sortedCards)) {
                        Hand(Rank.STRAIGHT_FLUSH, cards.sortedBy { -it.number })
                    } else {
                        Hand(Rank.STRAIGHT_FLUSH, sortedCards)
                    }
                }
                groupedCards[0].size == 4 -> {
                    Hand(Rank.FOUR_OF_A_KIND,
                        mainCards = sortedCards.filter { it in groupedCards[0] },
                        kickers = sortedCards.filterNot { it in groupedCards[0] })
                }
                groupedCards[0].size == 3 && groupedCards.size == 2 -> {
                    Hand(Rank.FULL_HOUSE,
                        mainCards = sortedCards.filter { it in groupedCards[0] },
                        subCards = sortedCards.filterNot { it in groupedCards[0] })
                }
                flush -> {
                    Hand(Rank.FLUSH, sortedCards)
                }
                straight -> {
                    if (!isRoyalStraight(sortedCards)) {
                        Hand(Rank.STRAIGHT, cards.sortedBy { -it.number })
                    } else {
                        Hand(Rank.STRAIGHT, sortedCards)
                    }
                }
                groupedCards[0].size == 3 -> {
                    Hand(Rank.THREE_OF_A_KIND,
                        mainCards = sortedCards.filter { it in groupedCards[0] },
                        kickers = sortedCards.filterNot { it in groupedCards[0] })
                }
                groupedCards[0].size == 2 && groupedCards.size == 3 -> {
                    Hand(Rank.TWO_PAIR,
                        mainCards = sortedCards.filter { it in groupedCards[0] },
                        subCards = sortedCards.filter { it in groupedCards[1] },
                        kickers = sortedCards.filter { it in groupedCards[2] })
                }
                groupedCards[0].size == 2 -> {
                    Hand(Rank.ONE_PAIR,
                        mainCards = sortedCards.filter { it in groupedCards[0] },
                        kickers = sortedCards.filterNot { it in groupedCards[0] })
                }
                else -> Hand(Rank.HIGH_CARD, sortedCards)
            }
        }

        private fun isRoyalStraight(cards: List<Card>): Boolean {
            return cards.map { it.number } == listOf(1, 13, 12, 11, 10)
        }

        private fun isStraight(cards: List<Card>): Boolean {
            if (isRoyalStraight(cards)) {
                return true
            }

            with(cards.sortedByDescending { it.number }) {
                for (i in 0..this.size - 2) {
                    if (this[i].number != this[i + 1].number + 1) {
                        return false
                    }
                }
                return true
            }
        }
    }
}
