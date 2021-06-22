package com.enigmacamp.mysimpleespresso.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.mysimpleespresso.R
import com.enigmacamp.mysimpleespresso.data.Spent
import com.enigmacamp.mysimpleespresso.databinding.ItemSpentBinding

class SpentViewAdapter(val data: List<Spent>) :
    RecyclerView.Adapter<SpentViewAdapter.SpentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SpentViewHolder(inflater.inflate(R.layout.item_spent, parent, false))
    }

    override fun onBindViewHolder(holder: SpentViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class SpentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemSpentBinding.bind(view)

        init {
            view.setOnClickListener {

            }
        }

        fun bind(spent: Spent) {
            binding.apply {
                textViewSpentAmount.text = spent.spentAmount.toString()
                textViewSpentDescription.text = spent.spentDescription
            }
        }
    }
}