package com.example.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.core_api.mediators.ExpensesTrackerApp
import com.example.core_api.mediators.GeneralScreenNavigator
import com.example.main.di.MainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var generalScreenNavigator: GeneralScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainComponent.create((application as ExpensesTrackerApp).getFacade()).inject(this)

        generalScreenNavigator.startGeneralScreen(
            R.id.main_container,
            supportFragmentManager
        )

//        val generalFragment = GeneralFragment()
//        val addExpenseFragment = AddExpenseFragment()
//        val categoryFragment = CategoryFragment()
//        val expensesFragment = ExpensesFragment()
//        val categoryTransactionsFragment = CategoryTransactionsFragment()
//        val incomesFragment = IncomesFragment()

//        supportFragmentManager.beginTransaction().add(
//            R.id.main_container,
//            incomesFragment,
//            ""
//        ).commit()
    }
}

