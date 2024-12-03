package com.example.core_api.mediators

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface CategoryScreenNavigator {

    fun startExpensesCategoryScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)

    fun startIncomesCategoryScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}