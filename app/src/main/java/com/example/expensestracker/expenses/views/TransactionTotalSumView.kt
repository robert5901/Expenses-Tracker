package com.example.expensestracker.expenses.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.R
import com.example.expensestracker.databinding.TransactionTotalSumBinding

class TransactionTotalSumView@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding by viewBinding(TransactionTotalSumBinding::bind)

    init {
        inflate(context, R.layout.transaction_total_sum, this)
    }

    fun setTotalSum(totalSum: String) = with(binding) {
        sum.text = totalSum
    }
}