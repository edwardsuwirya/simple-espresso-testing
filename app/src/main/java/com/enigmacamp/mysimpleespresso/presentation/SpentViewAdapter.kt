package com.enigmacamp.mysimpleespresso.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.mysimpleespresso.R
import com.enigmacamp.mysimpleespresso.data.Spent
import com.enigmacamp.mysimpleespresso.databinding.ItemSpentBinding
import com.enigmacamp.mysimpleespresso.utils.CountingIdlingResourceSingleton

class SpentViewAdapter(val data: List<Spent>, val spentSelection: (Spent) -> Unit) :
    RecyclerView.Adapter<SpentViewAdapter.SpentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SpentViewHolder(inflater.inflate(R.layout.item_spent, parent, false)) {
            spentSelection(data[it])
        }
    }

    override fun onBindViewHolder(holder: SpentViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class SpentViewHolder(view: View, val onItemClicked: (Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val binding = ItemSpentBinding.bind(view)

        init {
            view.setOnClickListener {
                onItemClicked(adapterPosition)
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