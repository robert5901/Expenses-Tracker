package com.example.expensestracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expensestracker.category.CategoryFragment
import com.example.expensestracker.expenses.ExpensesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generalFragment = GeneralFragment()
        val addExpenseFragment = AddExpenseFragment()
        val categoryFragment = CategoryFragment()
        val expensesFragment = ExpensesFragment()

        supportFragmentManager.beginTransaction().add(
            R.id.main_container,
            expensesFragment,
            ""
        ).commit()
    }
}

