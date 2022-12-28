package com.example.mydrinksapp.ui.view.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mydrinksapp.domain.model.Drink
import com.example.mydrinksapp.ui.view.viewholder.ItemCardViewHolder

class DrinkAdapter(private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<ItemCardViewHolder>() {

    private var items: MutableList<Drink> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Drink>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Drink {
        return this.items[position]
    }

    fun delete(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position);
    }

    override fun getItemCount() = this.items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemCardViewHolder.from(parent)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ItemCardViewHolder, position: Int) =
        holder.bind(this.items[position], listener)

}