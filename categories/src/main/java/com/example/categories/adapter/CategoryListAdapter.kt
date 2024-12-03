package com.example.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.categories.R
import com.example.categories.databinding.ListItemCategoryBinding

class CategoryListAdapter :
    ListAdapter<String, CategoryListAdapter.CategoryViewHolder>(CategoryDiffUtil()) {

    var onItemClickAction: ((String) -> Unit)? = null
    var onRenameItemClicked: ((String) -> Unit)? = null
    var onDeleteItemClicked: ((String) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        return CategoryViewHolder(
            ListItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    inner class CategoryViewHolder(private val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickAction?.invoke(getItem(adapterPosition))
            }

            binding.settings.setOnClickListener { view ->
                showMenu(view, R.menu.category_menu)
            }
        }

        fun onBind(item: String) {
            binding.title.text = item
        }

        private fun showMenu(view: View, @MenuRes menuRes: Int) {
            val popup = PopupMenu(view.context, view)
            popup.menuInflater.inflate(menuRes, popup.menu)

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.category_menu_rename -> {
                        onRenameItemClicked?.invoke(getItem(adapterPosition))
                        true
                    }

                    R.id.category_menu_delete -> {
                        onDeleteItemClicked?.invoke(getItem(adapterPosition))
                        true
                    }

                    else -> false
                }
            }

            popup.show()
        }
    }

    private class CategoryDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}