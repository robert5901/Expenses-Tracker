package com.example.expensestracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expensestracker.category.CategoryFragment
import com.example.expensestracker.categoryTransactions.CategoryTransactionsFragment
import com.example.expensestracker.expenses.ExpensesFragment
import com.example.expensestracker.incomes.IncomesFragment
import com.example.expensestracker.pieChart.PieChartFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generalFragment = GeneralFragment()
        val addExpenseFragment = AddExpenseFragment()
        val categoryFragment = CategoryFragment()
        val expensesFragment = ExpensesFragment()
        val categoryTransactionsFragment = CategoryTransactionsFragment()
        val incomesFragment = IncomesFragment()
        val pieChartFragment = PieChartFragment()

        supportFragmentManager.beginTransaction().add(
            R.id.main_container,
            pieChartFragment,
            ""
        ).commit()
    }
}

