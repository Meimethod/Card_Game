package cn.edu.sicnu.cs.lrj.cardgame

class Card(val suit: String, val rank: String) {
    var isChosen = false
    var isMatched = false

    companion object {
        val suits = arrayOf("♥", "♦", "♠", "♣")
        val ranks = arrayOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    }

    fun match(otherCards: Array<Card>): Int {
        var score = 0
        val other = otherCards[0]
        val sameSuit = (this.suit == other.suit)
        val sameRank = (this.rank == other.rank)

        if (sameSuit && sameRank) {
            //花色、点数全部相同
            score = 19
        } else if (sameRank) {
            //仅点数相同
            score = 15
        } else if (sameSuit) {
            //仅花色相同
            score = 4
        } else {
            //完全不匹配
            score = 0
        }
        return score
    }


    override fun toString(): String {
        return "$suit$rank"
    }
}
