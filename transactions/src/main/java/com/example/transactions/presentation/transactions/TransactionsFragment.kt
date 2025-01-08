package com.example.transactions.presentation.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.transactions.R
import com.example.transactions.presentation.transactions.adapters.TransactionCategoriesAdapter
import com.example.transactions.presentation.transactions.views.TransactionPeriodType
import com.example.transactions.databinding.TransactionsLayoutBinding

class TransactionsFragment : Fragment() {

    private val binding by viewBinding(TransactionsLayoutBinding::bind)
    private val adapter = TransactionCategoriesAdapter()

    private val viewModel: TransactionListViewModel by viewModels(
        ownerProducer = { requireParentFragment().requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadTransactionCategoryList(TransactionPeriodType.DAY)

        initViews()
        initRecyclerView()
        configureListeners()
        configureObservers()
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    private fun initViews() = with(binding) {
        val firstItem = resources.getStringArray(R.array.transactions_periods).first()
        title.setText(firstItem, false)

        transactionPeriodView.setPeriod(TransactionPeriodType.DAY)

        transactionPeriodView.onDateChanged = { date, transactionPeriodType ->
            viewModel.filterTransactionCategoryByPeriod(transactionPeriodType, date)
        }
        transactionPeriodView.onStartPeriodDateChanged = { startDate, transactionPeriodType ->
            viewModel.filterTransactionCategoryByPeriod(transactionPeriodType, startDate)
        }
        transactionPeriodView.onEndPeriodDateChanged = { endDate, transactionPeriodType ->
            viewModel.filterTransactionCategoryByPeriod(transactionPeriodType, endDate)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter

        adapter.onItemClicked = {
            // TODO open CategoryTransactionsFragment
        }
    }

    private fun configureListeners() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.title.setOnItemClickListener { _, _, position, _ ->
            binding.transactionPeriodView.isVisible = position != 5

            val transactionPeriodType = when (position) {
                0 -> TransactionPeriodType.DAY
                1 -> TransactionPeriodType.WEEK
                2 -> TransactionPeriodType.MONTH
                3 -> TransactionPeriodType.YEAR
                4 -> TransactionPeriodType.PERIOD
                5 -> TransactionPeriodType.All
                else -> TransactionPeriodType.DAY
            }

            binding.transactionPeriodView.setPeriod(transactionPeriodType)
            viewModel.filterTransactionCategoryByPeriod(transactionPeriodType)
        }
    }

    private fun configureObservers() {
        viewModel.transactionCategoryList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.totalAmount.observe(viewLifecycleOwner) {
            binding.transactionTotalAmountView.setTotalAmount(it.toString())
        }
    }
}