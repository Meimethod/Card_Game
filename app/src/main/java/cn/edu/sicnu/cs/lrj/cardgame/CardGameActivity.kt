package cn.edu.sicnu.cs.lrj.cardgame

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CardGameActivity : AppCompatActivity() {
    private lateinit var game: CardMatchingGame
    private lateinit var tvScore: TextView
    private lateinit var gridCards: RecyclerView
    private lateinit var adapter: CardAdapter
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_game)

        tvScore = findViewById(R.id.tv_score)
        gridCards = findViewById(R.id.grid_cards)
        val btnReset:Button = findViewById(R.id.btn_reset)
        val btnExit:Button = findViewById(R.id.btn_exit)

        initGame()

        btnReset.setOnClickListener {
            game.reset(24)
            refreshUI()
        }
        btnExit.setOnClickListener {
            finish()
        }
    }

    private fun initGame(){
        game = CardMatchingGame(24)
        setGridByOrientation()
        adapter = CardAdapter(game.cards){ index ->
            val result = game.chooseCardAtIndex(index)
            if(result){
                refreshUI()
                // 修改这里！！
                if(game.isThreeOpened && !game.isCurrentMatched()){
                    handler.postDelayed({
                        game.hideUnmatchedCards()
                        refreshUI()
                    }, 800)
                }

            }
        }

        gridCards.adapter = adapter
        refreshUI()
    }

    private fun setGridByOrientation(){
        val orientation = resources.configuration.orientation
        val spanCount = if(orientation == Configuration.ORIENTATION_LANDSCAPE) 6 else 4
        gridCards.layoutManager = GridLayoutManager(this, spanCount)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setGridByOrientation()
    }

    private fun refreshUI(){
        tvScore.text = "当前分数：${game.score}"
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
