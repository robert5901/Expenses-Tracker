package com.example.expensestracker.incomes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.R
import com.example.expensestracker.databinding.TransactionsLayoutBinding
import com.example.expensestracker.expenses.adapters.TransactionCategoriesAdapter
import com.example.expensestracker.expenses.models.TransactionCategory
import com.example.expensestracker.expenses.views.TransactionPeriodType

class IncomesFragment: Fragment() {

    private val binding by viewBinding(TransactionsLayoutBinding::bind)
    private val adapter = TransactionCategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initRecyclerView()
        configureListeners()
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    private fun initViews() = with(binding) {
        val firstItem = resources.getStringArray(R.array.transactions_periods).first()
        title.setText(firstItem, false)

        transactionPeriodView.setPeriod(TransactionPeriodType.DAY)
        transactionTotalAmountView.setTotalAmount("566 116")
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter

        adapter.onItemClicked = {
            // TODO open CategoryTransactionsFragment
        }

        adapter.submitList(getTestList())
    }

    private fun configureListeners() {
        binding.title.setOnItemClickListener { _, _, position, _ ->
            if (position == 5) {
                binding.transactionPeriodView.isVisible = false
                return@setOnItemClickListener
            } else {
                binding.transactionPeriodView.isVisible = true
            }

            val transactionPeriodType = when (position) {
                0 -> TransactionPeriodType.DAY
                1 -> TransactionPeriodType.WEEK
                2 -> TransactionPeriodType.MONTH
                3 -> TransactionPeriodType.YEAR
                4 -> TransactionPeriodType.PERIOD
                else -> TransactionPeriodType.DAY
            }

            binding.transactionPeriodView.setPeriod(transactionPeriodType)
        }
    }

    // TODO test data
    private fun getTestList() =
        listOf(
            TransactionCategory("Зарплата", 400000),
            TransactionCategory("Аванс", 100000),
            TransactionCategory("Отпусные", 0),
            TransactionCategory("Другое", 66116)
        )
}