package com.example.general.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_api.mediators.AddTransactionScreenNavigator
import com.example.core_api.mediators.ExpensesTrackerApp
import com.example.core_api.mediators.TransactionsScreenNavigator
import com.example.general.R
import com.example.general.databinding.FragmentGeneralBinding
import com.example.general.di.GeneralComponent
import com.example.general.presentation.views.GeneralItemViewType
import javax.inject.Inject
import com.example.core_api.R as core_R

class GeneralFragment : Fragment() {

    @Inject
    lateinit var addTransactionScreenNavigator: AddTransactionScreenNavigator

    @Inject
    lateinit var transactionsScreenNavigator: TransactionsScreenNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GeneralViewModel by viewModels {
        viewModelFactory
    }

    private val binding by viewBinding(FragmentGeneralBinding::bind)

    override fun onAttach(context: Context) {
        GeneralComponent.create(
            (requireActivity().application as ExpensesTrackerApp).getFacade()
        ).inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureObservers()

        binding.apply {

            incomes.setItem(GeneralItemViewType.INCOMES, listOf(0.0))
            balance.setItem(GeneralItemViewType.BALANCE, listOf(0.0))

            expenses.onClick = {
                addTransactionScreenNavigator.startAddExpenseScreen(
                    core_R.id.main_container,
                    parentFragmentManager
                )
            }
            incomes.onClick = {
                addTransactionScreenNavigator.startAddIncomeScreen(
                    core_R.id.main_container,
                    parentFragmentManager
                )
            }
            balance.onClick = {
                // TODO open BalanceFragment
            }

            expensesButton.setOnClickListener {
                transactionsScreenNavigator.startExpensesScreen(
                    core_R.id.main_container,
                    parentFragmentManager
                )
            }

            incomesButton.setOnClickListener {
                transactionsScreenNavigator.startIncomesScreen(
                    core_R.id.main_container,
                    parentFragmentManager
                )
            }
        }
    }

    private fun configureObservers() {
        viewModel.expensesTotalByPeriods.observe(viewLifecycleOwner) {
            binding.expenses.setItem(GeneralItemViewType.EXPENSES, it)
        }

        viewModel.incomeMonthTotal.observe(viewLifecycleOwner) {
            binding.incomes.setItem(GeneralItemViewType.EXPENSES, it)
        }
    }
}