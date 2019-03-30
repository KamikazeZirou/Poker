import com.poker.core.Card
import com.poker.core.Hand
import com.poker.core.Suit

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
}

