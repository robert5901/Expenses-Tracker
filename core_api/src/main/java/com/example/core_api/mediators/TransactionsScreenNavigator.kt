package com.example.core_api.mediators

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface TransactionsScreenNavigator {

    fun startExpensesScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)

    fun startIncomesScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}