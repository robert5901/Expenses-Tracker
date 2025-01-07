package com.example.transactions.presentation.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.transactions.R
import com.example.transactions.databinding.TransactionTotalAmountBinding

class TransactionTotalAmountView@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding by viewBinding(TransactionTotalAmountBinding::bind)

    init {
        inflate(context, R.layout.transaction_total_amount, this)
    }

    fun setTotalAmount(totalAmount: String) = with(binding) {
        amount.text = totalAmount
    }
}