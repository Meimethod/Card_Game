package cn.edu.sicnu.cs.lrj.cardgame
import kotlin.random.Random

class Deck {
    private val cards = mutableListOf<Card>()

    init {
        for (suit in Card.suits) {
            for (rank in Card.ranks) {
                cards.add(Card(suit, rank))
            }
        }
    }

    fun drawRandomCard(): Card? {
        if (cards.isEmpty()) return null
        val randomIndex = Random.nextInt(cards.size)
        return cards.removeAt(randomIndex)
    }
}
