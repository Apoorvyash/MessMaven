package com.androrubin.messmavendemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class previousTransAdapter(private val items:ArrayList<PreviousTransactionData>): RecyclerView.Adapter<adapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.previous_transaction_item,parent,false)
        return adapterHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: adapterHolder, position: Int) {
        val currentitem=items[position]
        holder.DinnerExtra.text=currentitem.DinnerExtra
        holder.LunchExtra.text=currentitem.LunchExtra
        holder.BreakfastExtra.text=currentitem.BreakfastExtra
        holder.DinnerAmt.text=currentitem.DinnerExtra
        holder.LunchAmt.text=currentitem.LunchAmt
        holder.BreakfastAmt.text=currentitem.BreakfastAmt
        holder.Date.text=currentitem.Date
        val isvisible:Boolean=currentitem.Visibility
        holder.constrainLayout.visibility=if(isvisible){
            View.VISIBLE
        } else View.GONE

        holder.visibility.setOnClickListener {
            currentitem.Visibility = !currentitem.Visibility
            notifyItemChanged(position)
        }
    }
}

class adapterHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val DinnerExtra:TextView=itemView.findViewById(R.id.dinnerextra)
    val LunchExtra:TextView=itemView.findViewById(R.id.extraLunch)
    val BreakfastExtra:TextView=itemView.findViewById(R.id.extraBreakfast)
    val DinnerAmt:TextView=itemView.findViewById(R.id.amtDinner)
    val LunchAmt:TextView=itemView.findViewById(R.id.amtLunch)
    val BreakfastAmt:TextView=itemView.findViewById(R.id.amtBreakfast)
    val Date:TextView=itemView.findViewById(R.id.tv_date)
    val visibility:ImageView=itemView.findViewById(R.id.imageView)
    val constrainLayout:ConstraintLayout=itemView.findViewById(R.id.expandedLayout)

}