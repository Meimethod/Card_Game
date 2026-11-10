package cn.edu.sicnu.cs.lrj.cardgame

class CardMatchingGame(private var totalCardCount: Int) {
    var score = 0
    val cards = mutableListOf<Card>()
    private val openedIndexList = mutableListOf<Int>()

    // 标记：是否正在等待翻面（延时期间禁止点击）
    var isWaiting = false
    val isThreeOpened: Boolean
        get() = openedIndexList.size == 3


    init {
        createCardList()
    }

    fun reset(newCardNum: Int) {
        totalCardCount = newCardNum
        score = 0
        cards.clear()
        openedIndexList.clear()
        isWaiting = false
        createCardList()
    }
    fun isCurrentMatched():Boolean{
        if(openedIndexList.size !=3) return false
        val c1 = cards[openedIndexList[0]]
        return c1.isMatched
    }


    private fun createCardList() {
        val suits = listOf("♠", "♥", "♣", "♦")
        val ranks = listOf("A", "2", "3", "4", "5", "6")
        for (suit in suits) {
            for (rank in ranks) {
                if (cards.size < totalCardCount) {
                    cards.add(Card(suit, rank))
                }
            }
        }
        cards.shuffle()
    }

    fun chooseCardAtIndex(index: Int): Boolean {
        // 如果正在延时等待，直接拦截点击
        if(isWaiting) return false

        val clickedCard = cards[index]
        if (clickedCard.isMatched || clickedCard.isFaceUp) return false

        score -= 1
        clickedCard.isFaceUp = true
        openedIndexList.add(index)

        // 集齐3张牌
        if (openedIndexList.size == 3) {
            isWaiting = true // 开启等待锁定，禁止继续点牌
            val c1 = cards[openedIndexList[0]]
            val c2 = cards[openedIndexList[1]]
            val c3 = cards[openedIndexList[2]]
            val matchResult = c1.match(c2, c3)

            when (matchResult) {
                1 -> score += 6    // 三张花色相同
                2 -> score += 18   // 三张点数相同
                3 -> score += 25   // 三张完全一样
            }

            if (matchResult > 0) {
                // 匹配成功，锁定卡牌
                c1.isMatched = true
                c2.isMatched = true
                c3.isMatched = true
                openedIndexList.clear()
                isWaiting = false
                return true
            } else {
                // 不匹配，交给Activity延时之后翻面
                return true
            }
        }
        return true
    }

    // 延时结束调用：三张不匹配，翻回背面
    fun hideUnmatchedCards(){
        for(idx in openedIndexList){
            cards[idx].isFaceUp = false
        }
        openedIndexList.clear()
        isWaiting = false
    }
}
