package com.example.transactions.navigation

import androidx.fragment.app.FragmentManager
import com.example.core_api.mediators.TransactionsScreenNavigator
import com.example.transactions.presentation.TransactionListMainFragment
import com.example.transactions.presentation.models.TransactionType
import com.example.ui_atoms.R
import javax.inject.Inject

class TransactionsScreenNavigatorImpl @Inject constructor() : TransactionsScreenNavigator {

    override fun startExpensesScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
            .replace(
                containerId,
                TransactionListMainFragment.newInstance(
                    TransactionType.EXPENSES
                )
            )
            .addToBackStack("ExpensesScreen")
            .commit()
    }

    override fun startIncomesScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
            .replace(
                containerId,
                TransactionListMainFragment.newInstance(
                    TransactionType.INCOMES
                )
            )
            .addToBackStack("ExpensesScreen")
            .commit()
    }
}