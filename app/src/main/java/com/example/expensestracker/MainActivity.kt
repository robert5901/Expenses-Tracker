package com.example.expensestracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.category.CategoryFragment
import com.example.expensestracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generalFragment = GeneralFragment()
        val addExpenseFragment = AddExpenseFragment()
        val сategoryFragment = CategoryFragment()

        supportFragmentManager.beginTransaction().add(
            R.id.main_container,
            сategoryFragment,
            ""
        ).commit()
    }
}

