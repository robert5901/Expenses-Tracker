package com.example.add_transaction.presentation.currency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.add_transaction.databinding.ListItemCurrencyBinding
import com.example.add_transaction.presentation.models.Currency

class CurrencyListAdapter :
    ListAdapter<Currency, CurrencyListAdapter.CurrencyViewHolder>(CurrencyDiffUtil()) {

    var onItemClickAction: ((Currency) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyViewHolder {
        return CurrencyViewHolder(
            ListItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    inner class CurrencyViewHolder(private val binding: ListItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickAction?.invoke(getItem(adapterPosition))
            }
        }

        fun onBind(item: Currency) {
            binding.title.text = item.title
            binding.description.text = item.description
        }
    }

    private class CurrencyDiffUtil : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }
    }
}