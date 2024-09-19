package com.example.expensestracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generalFragment = GeneralFragment()
        val addExpenseFragment = AddExpenseFragment()

        supportFragmentManager.beginTransaction().add(
            R.id.main_container,
            addExpenseFragment,
            ""
        ).commit()
    }
}

