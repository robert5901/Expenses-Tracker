package com.example.expensestracker.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.R
import com.example.expensestracker.category.adapter.CategoryListAdapter
import com.example.expensestracker.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private val binding by viewBinding(FragmentCategoryBinding::bind)

    private val adapter = CategoryListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
    }

    private fun initRecycler() {
        binding.recyclerView.adapter = adapter

        adapter.onItemClickAction = { item ->
            // TODO choose this category
        }
        adapter.onRenameItemClicked = { item ->
            // TODO rename this item
        }
        adapter.onDeleteItemClicked = { item ->
            // TODO delete this item
        }

        adapter.submitList(getTestList())
    }

    // TODO test data
    private fun getTestList() =
        listOf(
            "Транспорт",
            "Продукты",
            "Еда",
            "Квартира",
            "Одежда",
            "Подарки",
            "Развлечения",
            "Разное",
        )
}