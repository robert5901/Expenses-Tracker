package com.example.add_transaction.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.add_transaction.R
import com.example.add_transaction.di.AddTransactionComponent
import com.example.add_transaction.di.AddTransactionFactory
import com.example.add_transaction.presentation.models.TransactionType
import com.example.add_transaction.presentation.addTransaction.AddTransactionViewModel
import com.example.core_api.mediators.ExpensesTrackerApp
import javax.inject.Inject

class AddTransactionMainFragment : Fragment() {

    @Inject
    @AddTransactionFactory
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AddTransactionViewModel by viewModels {
        viewModelFactory
    }

    private var transactionType: TransactionType = TransactionType.EXPENSES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        transactionType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ADD_TRANSACTION_TYPE_KEY, TransactionType::class.java)
                ?: TransactionType.EXPENSES
        } else {
            arguments?.getParcelable(ADD_TRANSACTION_TYPE_KEY) ?: TransactionType.EXPENSES
        }
    }

    override fun onAttach(context: Context) {
        AddTransactionComponent.create(
            (requireActivity().application as ExpensesTrackerApp).getFacade()
        ).inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_transaction_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setTransactionType(transactionType)
    }

    companion object {
        private const val ADD_TRANSACTION_TYPE_KEY = "AddTransactionType"

        fun newInstance(transactionType: TransactionType): AddTransactionMainFragment {
            val fragment = AddTransactionMainFragment()
            val args = Bundle().apply {
                putParcelable(ADD_TRANSACTION_TYPE_KEY, transactionType)
            }
            fragment.arguments = args
            return fragment
        }
    }
}