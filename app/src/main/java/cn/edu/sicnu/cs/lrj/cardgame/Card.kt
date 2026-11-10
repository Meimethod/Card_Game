package cn.edu.sicnu.cs.lrj.cardgame

class Card(val suit: String, val rank: String) {
    var isFaceUp = false
    var isMatched = false

    //三张卡牌匹配逻辑
    fun match(card2:Card, card3:Card): Int{
        //全部三张花色点数都一样
        if(this.suit == card2.suit && this.rank == card2.rank && card2.suit == card3.suit && card2.rank == card3.rank){
            return 3
        }
        //三张花色全部相同
        else if(this.suit == card2.suit && card2.suit == card3.suit){
            return 1
        }
        //三张点数全部相同
        else if(this.rank == card2.rank && card2.rank == card3.rank){
            return 2
        }
        return 0
    }

    override fun toString(): String {
        return "$suit$rank"
    }
}
