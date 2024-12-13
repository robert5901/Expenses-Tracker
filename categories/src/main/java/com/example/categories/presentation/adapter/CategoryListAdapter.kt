package com.example.categories.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.categories.R
import com.example.categories.databinding.ListItemCategoryBinding
import com.example.categories.presentation.models.Category

class CategoryListAdapter :
    ListAdapter<Category, CategoryListAdapter.CategoryViewHolder>(CategoryDiffUtil()) {

    var onItemClickAction: ((Category) -> Unit)? = null
    var onRenameItemClicked: ((Category) -> Unit)? = null
    var onDeleteItemClicked: ((Category) -> Unit)? = null

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

        fun onBind(item: Category) {
            binding.title.text = item.name
            binding.settings.isVisible = true
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

    private class CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}