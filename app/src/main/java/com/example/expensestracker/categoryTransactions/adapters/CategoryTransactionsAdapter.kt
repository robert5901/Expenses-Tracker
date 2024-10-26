package com.example.expensestracker.categoryTransactions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensestracker.R
import com.example.expensestracker.categoryTransactions.models.CategoryTransaction
import com.example.expensestracker.databinding.ListItemCategoryTransactionBinding
import java.text.SimpleDateFormat
import java.util.Locale

class CategoryTransactionsAdapter :
    ListAdapter<CategoryTransaction, CategoryTransactionsAdapter.CategoryTransactionsViewHolder>(
        CategoryTransactionsDiffUtil()
    ) {

    var onDeleteClicked: ((String) -> Unit)? = null
    var onChangeClicked: ((String) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryTransactionsViewHolder {
        return CategoryTransactionsViewHolder(
            ListItemCategoryTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryTransactionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryTransactionsViewHolder(
        private val binding: ListItemCategoryTransactionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.settings.setOnClickListener { view ->
                showMenu(view, R.menu.category_transaction_menu)
            }
        }

        fun bind(item: CategoryTransaction) = with(binding) {
            val formatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale("ru"))
            val formatedDate = formatter.format(item.date)

            date.text = formatedDate
            amount.text = item.amount.toString()
            currency.text = item.currency
            comment.text = item.comment
        }

        private fun showMenu(view: View, @MenuRes menuRes: Int) {
            val popup = PopupMenu(view.context, view)
            popup.menuInflater.inflate(menuRes, popup.menu)

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.category_transaction_menu_change -> {
                        onChangeClicked?.invoke(getItem(adapterPosition).id)
                        true
                    }

                    R.id.category_transaction_menu_delete -> {
                        onDeleteClicked?.invoke(getItem(adapterPosition).id)
                        true
                    }

                    else -> false
                }
            }

            popup.show()
        }
    }

    private class CategoryTransactionsDiffUtil : DiffUtil.ItemCallback<CategoryTransaction>() {
        override fun areItemsTheSame(
            oldItem: CategoryTransaction,
            newItem: CategoryTransaction
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CategoryTransaction,
            newItem: CategoryTransaction
        ): Boolean {
            return oldItem == newItem
        }
    }
}
