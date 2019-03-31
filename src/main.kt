import com.poker.core.*

fun main(args: Array<String>) {
    val straightFlush = Hand.parse(listOf(
        Card(Suit.CLUB, 1),
        Card(Suit.CLUB, 13),
        Card(Suit.CLUB, 12),
        Card(Suit.CLUB, 11),
        Card(Suit.CLUB, 10)
    ))
    println(straightFlush)

    val straightFlush2 = Hand.parse(listOf(
        Card(Suit.CLUB, 1),
        Card(Suit.CLUB, 2),
        Card(Suit.CLUB, 3),
        Card(Suit.CLUB, 4),
        Card(Suit.CLUB, 5)
    ))
    println(straightFlush2)


    val fourOfAKind = Hand.parse(listOf(
        Card(Suit.CLUB, 13),
        Card(Suit.HEART, 1),
        Card(Suit.CLUB, 1),
        Card(Suit.SPADE, 1),
        Card(Suit.DIAMOND, 1)
    ))
    println(fourOfAKind)

    val fullHouse = Hand.parse(listOf(
        Card(Suit.CLUB, 13),
        Card(Suit.SPADE, 13),
        Card(Suit.CLUB, 1),
        Card(Suit.SPADE, 1),
        Card(Suit.DIAMOND, 1)
    ))
    println(fullHouse)

    val fullHouse2 = Hand.parse(listOf(
        Card(Suit.CLUB, 1),
        Card(Suit.SPADE, 1),
        Card(Suit.CLUB, 13),
        Card(Suit.SPADE, 13),
        Card(Suit.DIAMOND, 13)
    ))
    println(fullHouse2)

    val flush = Hand.parse(listOf(
        Card(Suit.CLUB, 1),
        Card(Suit.CLUB, 13),
        Card(Suit.CLUB, 12),
        Card(Suit.CLUB, 9),
        Card(Suit.CLUB, 10)
    ))
    println(flush)

    val straight = Hand.parse(listOf(
        Card(Suit.CLUB, 1),
        Card(Suit.CLUB, 13),
        Card(Suit.SPADE, 12),
        Card(Suit.CLUB, 11),
        Card(Suit.CLUB, 10)
    ))
    println(straight)

    val threeOfAKind = Hand.parse(listOf(
        Card(Suit.CLUB, 1),
        Card(Suit.SPADE, 1),
        Card(Suit.HEART, 1),
        Card(Suit.CLUB, 11),
        Card(Suit.CLUB, 10)
    ))
    println(threeOfAKind)

    val twoPair = Hand.parse(listOf(
        Card(Suit.CLUB, 1),
        Card(Suit.SPADE, 1),
        Card(Suit.HEART, 13),
        Card(Suit.CLUB, 13),
        Card(Suit.CLUB, 10)
    ))
    println(twoPair)

    val onePair = Hand.parse(listOf(
        Card(Suit.CLUB, 1),
        Card(Suit.SPADE, 1),
        Card(Suit.HEART, 13),
        Card(Suit.CLUB, 12),
        Card(Suit.CLUB, 11)
    ))
    println(onePair)

    val highCard = Hand.parse(listOf(
        Card(Suit.CLUB, 1),
        Card(Suit.SPADE, 9),
        Card(Suit.HEART, 13),
        Card(Suit.CLUB, 12),
        Card(Suit.CLUB, 11)
    ))
    println(highCard)

    val deck = Deck()
    val player = Player()
    player.addCard(deck.draw())
    player.addCard(deck.draw())

    var communityCards: List<Card> = mutableListOf()
    for (i in 0 until 5) {
        communityCards += deck.draw()
    }

    val allCards = communityCards + player.cards
    val playerHand = allCards.combination(5).fold(Hand(Hand.Rank.NONE, listOf())) { strongestHand, cards ->
        Hand.parse(cards).takeIf { it > strongestHand } ?: strongestHand
    }
    println("Player's cards: ${player.cards.joinToString(",")}")
    println("Community cards: ${communityCards.joinToString(",")}")
    println("Hand: $playerHand")
}

private fun <T> List<T>.combination(n: Int): Iterable<List<T>> = Iterable { CombinationIterator(this, n) }

private class CombinationIterator<T>(val list: List<T>, val choiceNum: Int) : Iterator<List<T>> {
    private val indexes = list.indices.toList()
    private val elements = mutableListOf<Int>()
    private val stack = mutableListOf<Iterator<Int>>()

    init {
        if (list.size >= choiceNum) {
            stack.add(indexes.subList(0, list.size - choiceNum + 1).iterator())
            elements.add(stack.last().next())
        }
    }

    override fun hasNext(): Boolean {
        return !stack.isEmpty()
    }

    override fun next(): List<T> {
        var result: List<T>
        if (!stack.isEmpty()) {
            while (stack.size < choiceNum) {
                stack.add(indexes.subList(elements.last() + 1, list.size - choiceNum + 1 + stack.size).iterator())
                elements.add(stack.last().next())
            }

            result = elements.map { list[it] }

            while (!stack.isEmpty()) {
                if (stack.last().hasNext()) {
                    elements[stack.size - 1] = stack.last().next()
                    break
                } else {
                    stack.removeAt(stack.size - 1)
                    elements.removeAt(elements.size - 1)
                }
            }
        } else {
            throw NoSuchElementException()
        }
        return result
    }
}
