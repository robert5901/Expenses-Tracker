package com.example.add_transaction.presentation.currency

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.add_transaction.R
import com.example.add_transaction.databinding.FragmentCurrencyBinding
import com.example.add_transaction.di.AddTransactionComponent
import com.example.add_transaction.di.CurrencyFactory
import com.example.add_transaction.presentation.addTransaction.AddTransactionViewModel
import com.example.add_transaction.presentation.currency.adapter.CurrencyListAdapter
import com.example.core_api.mediators.ExpensesTrackerApp
import javax.inject.Inject

class CurrencyFragment: Fragment() {

    private val binding by viewBinding(FragmentCurrencyBinding::bind)

    @Inject
    @CurrencyFactory
    lateinit var currencyViewModelFactory: ViewModelProvider.Factory

    private val currencyViewModel: CurrencyViewModel by viewModels {
        currencyViewModelFactory
    }

    private val addTransactionMainViewModel: AddTransactionViewModel by viewModels(
        ownerProducer = {
            requireParentFragment().requireParentFragment()
        }
    )

    private val adapter = CurrencyListAdapter()

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
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        currencyViewModel.getCurrencyList()

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        initRecyclerView()
        initObservables()
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter

        adapter.onItemClickAction = { currency ->
            addTransactionMainViewModel.updateCurrency(currency)
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initObservables() {
        currencyViewModel.currencyList.observe(viewLifecycleOwner) { currencyList ->
            adapter.submitList(currencyList)
        }
    }
}