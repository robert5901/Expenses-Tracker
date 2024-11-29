package com.example.transactions.navigation

import androidx.fragment.app.FragmentManager
import com.example.core_api.mediators.TransactionsScreenNavigator
import com.example.transactions.TransactionsFragment
import com.example.ui_atoms.R
import javax.inject.Inject

class TransactionsScreenNavigatorImpl @Inject constructor() : TransactionsScreenNavigator {

    override fun startTransactionScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
            .add(containerId, TransactionsFragment())
            .addToBackStack("TransactionsFragment")
            .commit()
    }
}