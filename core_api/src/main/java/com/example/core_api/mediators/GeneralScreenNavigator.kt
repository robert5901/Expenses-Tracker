package com.example.core_api.mediators

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface GeneralScreenNavigator {

    fun startGeneralScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}