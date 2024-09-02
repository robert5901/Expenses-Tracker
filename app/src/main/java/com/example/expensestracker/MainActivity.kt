package com.example.expensestracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

