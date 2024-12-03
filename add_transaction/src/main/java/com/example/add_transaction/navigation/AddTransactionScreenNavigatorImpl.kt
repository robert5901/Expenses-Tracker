package com.example.add_transaction.navigation

import androidx.fragment.app.FragmentManager
import com.example.add_transaction.AddTransactionFragment
import com.example.core_api.mediators.AddTransactionScreenNavigator
import com.example.ui_atoms.R
import javax.inject.Inject

class AddTransactionScreenNavigatorImpl @Inject constructor() : AddTransactionScreenNavigator {

    override fun startAddExpenseScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
            .add(
                containerId,
                AddTransactionFragment.newInstance(
                    AddTransactionFragment.AddTransactionType.EXPENSES
                )
            )
            .addToBackStack("AddExpenseScreen")
            .commit()
    }

    override fun startAddIncomeScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
            .add(
                containerId,
                AddTransactionFragment.newInstance(
                    AddTransactionFragment.AddTransactionType.INCOMES
                )
            )
            .addToBackStack("AddIncomeScreen")
            .commit()
    }
}