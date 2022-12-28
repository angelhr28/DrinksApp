package com.example.mydrinksapp.ui.view.viewholder

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mydrinksapp.databinding.ItemCardBinding
import com.example.mydrinksapp.domain.model.Drink
import com.squareup.picasso.Picasso


class ItemCardViewHolder(private val binding: ItemCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): ItemCardViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCardBinding.inflate(inflater, parent, false)
            return ItemCardViewHolder(binding)
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    fun bind(drink: Drink, listener: (String) -> Unit) {
        binding.apply {
            title.text = drink.name
            desc.text = drink.instructions

            Picasso.get()
                .load(drink.img)
                .into(img);

            desc.text = drink.instructions
            root.setOnClickListener {
                listener(drink.img ?: "")
            }
        }
    }
}