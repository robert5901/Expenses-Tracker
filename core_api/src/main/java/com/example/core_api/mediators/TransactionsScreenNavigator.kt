package com.example.core_api.mediators

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface TransactionsScreenNavigator {

    fun startTransactionScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}