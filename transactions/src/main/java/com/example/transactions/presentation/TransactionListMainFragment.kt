package com.example.transactions.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core_api.mediators.ExpensesTrackerApp
import com.example.transactions.R
import com.example.transactions.di.TransactionsComponent
import com.example.transactions.presentation.models.TransactionType
import com.example.transactions.presentation.transactions.TransactionListViewModel
import javax.inject.Inject

class TransactionListMainFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: TransactionListViewModel by viewModels {
        viewModelFactory
    }

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

    override fun onAttach(context: Context) {
        TransactionsComponent.create(
            (requireActivity().application as ExpensesTrackerApp).getFacade()
        ).inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_list_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setTransactionType(transactionType)
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