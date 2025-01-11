package com.example.general.navigation

import androidx.fragment.app.FragmentManager
import com.example.core_api.mediators.GeneralScreenNavigator
import com.example.general.presentation.GeneralFragment
import javax.inject.Inject

class GeneralScreenNavigatorImpl @Inject constructor() : GeneralScreenNavigator {

    override fun startGeneralScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(containerId, GeneralFragment())
            .commit()
    }
}