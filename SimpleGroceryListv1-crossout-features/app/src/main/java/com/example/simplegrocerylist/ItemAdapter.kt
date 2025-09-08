package com.example.notes

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simplegrocerylist.Item
import com.example.simplegrocerylist.R


class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val items: ArrayList<Item> = ArrayList()
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(itemView)

    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int,
    ) {
        val currentItem = items[position]
        holder.titleTextView.text = currentItem.title
        holder.amountTextView.text = currentItem.amount
        when (currentItem.category) {
            1 -> {
                holder.categoryTextView.text = "Хлебный"
                holder.categoryTextView.setBackgroundResource(R.color.hleb)
            }

            2 -> {
                holder.categoryTextView.text = "Бакалея"
                holder.categoryTextView.setBackgroundResource(R.color.bakaleya)
            }

            3 -> {
                holder.categoryTextView.text = "Молочный"
                holder.categoryTextView.setBackgroundResource(R.color.milk)
            }

            4 -> {
                holder.categoryTextView.text = "Мясной"
                holder.categoryTextView.setBackgroundResource(R.color.meat)
            }

            6 -> {
                holder.categoryTextView.text = "Овощной"
                holder.categoryTextView.setBackgroundResource(R.color.vegetables)
            }

            5 -> {
                holder.categoryTextView.text = "Колбасный"
                holder.categoryTextView.setBackgroundResource(R.color.kolbasa)
            }

            7 -> {
                holder.categoryTextView.text = "Рыбный"
                holder.categoryTextView.setBackgroundResource(R.color.fish)
            }

            8 -> {
                holder.categoryTextView.text = "Сладости"
                holder.categoryTextView.setBackgroundResource(R.color.sweets)
            }

            9 -> {
                holder.categoryTextView.text = "Напитки"
                holder.categoryTextView.setBackgroundResource(R.color.drinks)
            }

            else -> holder.categoryTextView.text = "Ошибка"
        }
        if(currentItem.crossout){
        holder.titleTextView.setPaintFlags(holder.titleTextView.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
            holder.amountTextView.setPaintFlags(holder.amountTextView.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)}
        else {
            holder.titleTextView.setPaintFlags(holder.titleTextView.getPaintFlags() and  Paint.STRIKE_THRU_TEXT_FLAG.inv())
            holder.amountTextView.setPaintFlags(holder.amountTextView.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Item {
        return items[position]
    }

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        val amountTextView: TextView = itemView.findViewById(R.id.tv_amount)
        val categoryTextView: TextView = itemView.findViewById(R.id.tv_category)

        init {
            itemView.setOnClickListener {
                listener?.let { listener ->
                    val position: Int = adapterPosition
                    if (position in 0..itemCount) {
                        listener.onItemClick(items[position])
                    }
                }
            }
        }
    }

    fun setOnCLickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(item: Item?)
    }
}