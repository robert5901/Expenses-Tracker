package com.example.expensestracker.categoryTransactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.R
import com.example.expensestracker.categoryTransactions.adapters.CategoryTransactionsAdapter
import com.example.expensestracker.categoryTransactions.models.CategoryTransaction
import com.example.expensestracker.databinding.FragmentCategoryTransactionsBinding
import java.util.Date

class CategoryTransactionsFragment : Fragment() {

    private val binding by viewBinding(FragmentCategoryTransactionsBinding::bind)
    private val adapter = CategoryTransactionsAdapter()

    // TODO test data. get from args
    private val argsTitle = "Продукты"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = argsTitle

        binding.back.setOnClickListener {
            // TODO navigate back
        }

        binding.recyclerView.adapter = adapter

        adapter.onDeleteClicked = {
            // TODO delete transaction
        }

        adapter.onChangeClicked = {
            // TODO change transaction
        }

        adapter.submitList(getTestList())
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    // TODO test data
    private fun getTestList() =
        listOf(
            CategoryTransaction(
                id = "1",
                date = Date(),
                amount = 10000,
                currency = "RUB",
                comment = "Доставка лента"
            ),
            CategoryTransaction(
                id = "1",
                date = Date(),
                amount = 7000,
                currency = "RUB",
                comment = "Пятерочка"
            ),
            CategoryTransaction(
                id = "1",
                date = Date(),
                amount = 9800,
                currency = "RUB",
                comment = "Чижик"
            ),
            CategoryTransaction(
                id = "1",
                date = Date(),
                amount = 2000,
                currency = "RUB",
                comment = "Магнит"
            ),
        )
}