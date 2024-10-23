package com.example.expensestracker.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.R
import com.example.expensestracker.databinding.TransactionsLayoutBinding
import com.example.expensestracker.expenses.views.TransactionPeriodType

class ExpensesFragment : Fragment() {

    private val binding by viewBinding(TransactionsLayoutBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        configureListeners()
    }

    private fun initViews() = with(binding) {
        val firstItem = resources.getStringArray(R.array.transactions_periods).first()
        title.setText(firstItem, false)

        transactionPeriodView.setPeriod(TransactionPeriodType.DAY)
        transactionTotalSumView.setTotalSum("245 686,84")

    }

    private fun configureListeners() {
        binding.title.setOnItemClickListener { parent, view, position, id ->
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
}