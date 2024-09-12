package com.example.expensestracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.databinding.FragmentGeneralBinding
import views.GeneralItemViewType
import views.model.Expenses

class GeneralFragment : Fragment() {

    private val binding by viewBinding(FragmentGeneralBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO test data
        binding.expenses.setItem(GeneralItemViewType.EXPENSES, getAmountListForExpenses())
        binding.incomes.setItem(GeneralItemViewType.INCOMES, getAmountListForIncomes())
        binding.balance.setItem(GeneralItemViewType.BALANCE, getAmountListForBalance())
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