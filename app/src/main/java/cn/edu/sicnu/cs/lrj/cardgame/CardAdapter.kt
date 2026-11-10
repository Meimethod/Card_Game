package cn.edu.sicnu.cs.lrj.cardgame

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(
    private val cardList: List<Card>,
    private val onCardClick: (Int) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: TextView) : RecyclerView.ViewHolder(itemView) {
        val tvCard: TextView = itemView
        init {
            tvCard.setOnClickListener {
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION){
                    onCardClick(pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false) as TextView
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int = cardList.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        if(card.isFaceUp){
            holder.tvCard.text = card.toString()
            holder.tvCard.setBackgroundColor(0xFFE8F0FE.toInt())
        }else{
            holder.tvCard.text = "?"
            holder.tvCard.setBackgroundColor(0xFFEEEEEE.toInt())
        }
    }
}
