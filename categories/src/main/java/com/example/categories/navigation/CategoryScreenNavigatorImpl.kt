package com.example.categories.navigation

import androidx.fragment.app.FragmentManager
import com.example.categories.presentation.CategoryFragment
import com.example.categories.presentation.CategoryType
import com.example.core_api.mediators.CategoryScreenNavigator
import com.example.ui_atoms.R
import javax.inject.Inject

class CategoryScreenNavigatorImpl @Inject constructor() : CategoryScreenNavigator {

    override fun startExpensesCategoryScreen(
        containerId: Int,
        fragmentManager: FragmentManager,
        transactionId: Long
    ) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
            .add(
                containerId, CategoryFragment.newInstance(
                    CategoryType.EXPENSES,
                    transactionId
                )
            )
            .addToBackStack("ExpensesCategoryScreen")
            .commit()
    }

    override fun startIncomesCategoryScreen(
        containerId: Int,
        fragmentManager: FragmentManager,
        transactionId: Long
    ) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
            .add(
                containerId, CategoryFragment.newInstance(
                    CategoryType.INCOMES,
                    transactionId
                )
            )
            .addToBackStack("IncomesCategoryScreen")
            .commit()
    }
}