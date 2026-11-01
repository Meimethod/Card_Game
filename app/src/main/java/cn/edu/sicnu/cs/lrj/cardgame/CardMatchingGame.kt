package cn.edu.sicnu.cs.lrj.cardgame

class CardMatchingGame(private val cardCount: Int) {
    private val deck = Deck()
    val cards = mutableListOf<Card>()
    var score = 0

    companion object {
        const val MISMATCH_PENALTY = 2   //不匹配扣分
        const val COST_TO_CHOOSE = 1     //翻一张扣1分
    }


    init {
        repeat(cardCount) {
            deck.drawRandomCard()?.let {
                cards.add(it)
            }
        }
    }

    fun chooseCardAtIndex(index: Int) {
        val card = cards[index]
        if (card.isMatched) return
        if (card.isChosen) {
            card.isChosen = false
            return
        }

        for (otherCard in cards) {
            if (otherCard.isChosen && !otherCard.isMatched) {
                val matchScore = card.match(arrayOf(otherCard))
                if (matchScore > 0) {
                    score += matchScore
                    card.isMatched = true
                    otherCard.isMatched = true
                } else {
                    otherCard.isChosen = false
                }
                break
            }
        }
        score -= COST_TO_CHOOSE
        card.isChosen = true
    }


    fun cardAtIndex(index: Int): Card {
        return cards[index]
    }
}
