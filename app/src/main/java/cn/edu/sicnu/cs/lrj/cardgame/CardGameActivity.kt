package cn.edu.sicnu.cs.lrj.cardgame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CardGameActivity : AppCompatActivity() {
    private lateinit var tvScore: TextView
    private lateinit var gridCards: GridView
    private lateinit var game: CardMatchingGame
    private val cardNum = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_game)

        tvScore = findViewById(R.id.tv_score)
        gridCards = findViewById(R.id.grid_cards)
        val btnReset:Button = findViewById(R.id.btn_reset)
        val btnExit:Button = findViewById(R.id.btn_exit)

        initGame()

        btnReset.setOnClickListener {
            initGame()
        }
        btnExit.setOnClickListener {
            val intent = Intent()
            intent.putExtra("gameScore",game.score)
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    private fun initGame(){
        game = CardMatchingGame(cardNum)
        refreshGridView()
    }

    private fun refreshGridView(){
        tvScore.text = getString(R.string.score,game.score)
        gridCards.adapter = object : ArrayAdapter<Card>(this,0,game.cards){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val card = getItem(position)!!
                val btn:Button
                if(convertView == null){
                    btn = Button(context)
                    btn.layoutParams = ViewGroup.LayoutParams(180,180)
                }else{
                    btn = convertView as Button
                }
                //翻牌逻辑：选中显示卡牌，没选中显示问号背面
                if(card.isChosen){
                    btn.text = card.toString()
                }else{
                    btn.text = "❓"
                }
                btn.setOnClickListener {
                    game.chooseCardAtIndex(position)
                    refreshGridView()
                }
                return btn
            }
        }
    }
}
