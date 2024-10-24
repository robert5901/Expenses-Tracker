package com.example.expensestracker.expenses.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensestracker.databinding.ListItemTransactionCategoryBinding
import com.example.expensestracker.expenses.models.TransactionCategory

class TransactionCategoriesAdapter :
    ListAdapter<TransactionCategory, TransactionCategoriesAdapter.TransactionCategoriesViewHolder>(
        TransactionCategoriesDiffUtil()
    ) {

    var onItemClicked: ((TransactionCategory) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionCategoriesViewHolder {
        return TransactionCategoriesViewHolder(
            ListItemTransactionCategoryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionCategoriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class TransactionCategoriesViewHolder(
        private val binding: ListItemTransactionCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClicked?.invoke(getItem(adapterPosition))
            }
        }

        fun bind(item: TransactionCategory) = with(binding) {
            title.text = item.title
            amount.text = item.amount.toString()
        }
    }

    private class TransactionCategoriesDiffUtil : DiffUtil.ItemCallback<TransactionCategory>() {

        override fun areItemsTheSame(oldItem: TransactionCategory, newItem: TransactionCategory): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: TransactionCategory, newItem: TransactionCategory): Boolean {
            return oldItem == newItem
        }
    }
}