package com.example.core_api.mediators

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface AddTransactionScreenNavigator {

    fun startAddExpenseScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)

    fun startAddIncomeScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}