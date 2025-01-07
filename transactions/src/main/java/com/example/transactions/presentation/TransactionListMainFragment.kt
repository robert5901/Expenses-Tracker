package com.example.transactions.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.transactions.R
import com.example.transactions.presentation.models.TransactionType

class TransactionListMainFragment: Fragment() {

    private var transactionType: TransactionType = TransactionType.EXPENSES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        transactionType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(TRANSACTION_TYPE_KEY, TransactionType::class.java)
                ?: TransactionType.EXPENSES
        } else {
            arguments?.getParcelable(TRANSACTION_TYPE_KEY) ?: TransactionType.EXPENSES
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_list_main, container, false)
    }

    companion object {
        private const val TRANSACTION_TYPE_KEY = "TransactionType"

        fun newInstance(transactionType: TransactionType): TransactionListMainFragment {
            val fragment = TransactionListMainFragment()
            val args = Bundle().apply {
                putParcelable(TRANSACTION_TYPE_KEY, transactionType)
            }
            fragment.arguments = args
            return fragment
        }
    }
}