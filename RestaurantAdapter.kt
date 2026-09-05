package com.example.restaurantapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.databinding.RestaurantItemBinding

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    private val items = ArrayList<RestaurantItem>()

    class RestaurantViewHolder(val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RestaurantItem) {
            binding.iM.setImageResource(item.image)
            binding.tvName.text = item.name
            binding.tvCategory.text = "Категория: ${item.category}"
            binding.tvPrice.text = "Цена: ${item.price} руб."
            binding.tvRating.text = "Рейтинг: ${item.rating}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(newItems: List<RestaurantItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
