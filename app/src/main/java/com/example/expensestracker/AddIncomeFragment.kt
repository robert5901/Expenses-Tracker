package com.example.expensestracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.databinding.AddTransactionLayoutBinding

class AddIncomeFragment : Fragment() {
    private val binding by viewBinding(AddTransactionLayoutBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_transaction_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = resources.getString(R.string.add_expense_title)

        binding.category.setOnClickListener {
            // TODO navigate to category screen. And pass list of expenses categories
            // TODO or get list of Expenses or Incomes categories in the category screen by enum type
        }
    }
}