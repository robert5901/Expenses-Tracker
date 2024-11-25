package com.example.general.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.general.R
import com.example.general.databinding.GeneralItemAmountBinding
import com.example.general.domain.models.GeneralAmount

class GeneralItemAmountView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding by viewBinding(GeneralItemAmountBinding::bind)

    init {
        inflate(context, R.layout.general_item_amount, this)
    }

    fun setAmount(generalAmount: GeneralAmount, dividerVisible: Boolean) {
        with(binding) {
            title.text = generalAmount.title
            amount.text = generalAmount.amount.toString()

            divider.isVisible = dividerVisible
        }
    }
}