package com.example.general

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_api.mediators.AddTransactionScreenNavigator
import com.example.core_api.mediators.ExpensesTrackerApp
import com.example.general.databinding.FragmentGeneralBinding
import com.example.general.di.GeneralComponent
import com.example.general.domain.models.Expenses
import com.example.general.views.GeneralItemViewType
import javax.inject.Inject
import com.example.core_api.R as core_R

class GeneralFragment : Fragment() {

    @Inject
    lateinit var addTransactionScreenNavigator: AddTransactionScreenNavigator

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

        // TODO test data
        binding.apply {
            expenses.setItem(GeneralItemViewType.EXPENSES, getAmountListForExpenses())
            incomes.setItem(GeneralItemViewType.INCOMES, getAmountListForIncomes())
            balance.setItem(GeneralItemViewType.BALANCE, getAmountListForBalance())

            expenses.onClick = {
                // TODO open AddTransactionFragment and pass Expenses enum
                addTransactionScreenNavigator.startAddTransactionScreen(
                    core_R.id.main_container,
                    parentFragmentManager
                )
            }
            incomes.onClick = {
                // TODO open AddTransactionFragment and pass Incomes enum
            }
            balance.onClick = {
                // TODO open BalanceFragment
            }
        }
    }

    // TODO test data
    private fun getAmountListForExpenses(): List<Double> {
        return Expenses(
            100.0,
            200.0,
            300.0
        ).toList()
    }

    // TODO test data
    private fun getAmountListForIncomes(): List<Double> {
        return listOf(600.0)
    }

    // TODO test data
    private fun getAmountListForBalance(): List<Double> {
        return listOf(5000.0)
    }
}