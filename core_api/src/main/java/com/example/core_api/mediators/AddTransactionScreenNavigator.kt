package com.example.core_api.mediators

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface AddTransactionScreenNavigator {

    fun startAddTransactionScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}