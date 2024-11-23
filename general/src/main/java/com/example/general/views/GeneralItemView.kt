package com.example.general.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.general.R
import com.example.general.databinding.GeneralItemBinding
import com.example.general.domain.models.GeneralAmount

class GeneralItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding by viewBinding(GeneralItemBinding::bind)

    init {
        inflate(context, R.layout.general_item, this)
    }

    fun setItem(
        generalItemViewType: GeneralItemViewType,
        amountList: List<Double>
    ) {
        when (generalItemViewType) {
            GeneralItemViewType.EXPENSES -> {
                configureExpensesItem(amountList)
            }

            GeneralItemViewType.INCOMES -> {
                configureIncomesItem(amountList)
            }

            GeneralItemViewType.BALANCE -> {
                configureBalance(amountList)
            }
        }
    }

    private fun configureExpensesItem(amountList: List<Double>) {
        val expensesColor = ColorStateList.valueOf(
            ContextCompat.getColor(context, R.color.general_expenses)
        )

        binding.title.backgroundTintList = expensesColor
        binding.button.imageTintList = expensesColor
        binding.title.text = resources.getString(R.string.general_item_expenses_title)

        val titles = listOf(
            resources.getString(R.string.today_title),
            resources.getString(R.string.week_title),
            resources.getString(R.string.month_title)
        )

        val generalAmountList = titles.zip(amountList) { title, amount ->
            GeneralAmount(title, amount)
        }

        generalAmountList.forEach {
            if (it == generalAmountList.last()) {
                createAmountView(it, false)
                return@forEach
            }
            createAmountView(it)
        }
    }

    private fun configureIncomesItem(amountList: List<Double>) {
        val expensesColor = ColorStateList.valueOf(
            ContextCompat.getColor(context, R.color.general_incomes)
        )

        binding.title.backgroundTintList = expensesColor
        binding.button.imageTintList = expensesColor
        binding.title.text = resources.getString(R.string.general_item_incomes_title)

        createAmountView(
            GeneralAmount(
                title = resources.getString(R.string.month_title),
                amount = amountList.first()
            ),
            dividerVisible = false
        )
    }

    private fun configureBalance(amountList: List<Double>) {
        val expensesColor = ColorStateList.valueOf(
            ContextCompat.getColor(context, R.color.general_balance)
        )

        binding.title.backgroundTintList = expensesColor
        binding.button.imageTintList = expensesColor
        binding.title.text = resources.getString(R.string.general_item_balance_title)

        createAmountView(
            GeneralAmount(
                title = resources.getString(R.string.general_item_balance_amount_title),
                amount = amountList.first()
            ),
            dividerVisible = false
        )
    }

    private fun createAmountView(generalAmount: GeneralAmount, dividerVisible: Boolean = true) {
        val generalItemAmountView = GeneralItemAmountView(context)
        generalItemAmountView.setAmount(generalAmount, dividerVisible)

        binding.amountLayout.addView(generalItemAmountView)
    }
}

enum class GeneralItemViewType {
    EXPENSES,
    INCOMES,
    BALANCE
}