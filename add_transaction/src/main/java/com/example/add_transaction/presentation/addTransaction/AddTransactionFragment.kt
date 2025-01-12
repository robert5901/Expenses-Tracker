package com.example.add_transaction.presentation.addTransaction

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.add_transaction.R
import com.example.add_transaction.databinding.AddTransactionLayoutBinding
import com.example.add_transaction.presentation.models.TransactionType
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddTransactionFragment : Fragment() {
    private val binding by viewBinding(AddTransactionLayoutBinding::bind)

    private val viewModel: AddTransactionViewModel by viewModels(
        ownerProducer = {
            requireParentFragment().requireParentFragment()
        }
    )

    private var transactionType: TransactionType? = null
    private var selectedDate = Date()
    private var selectedTime = Date()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_transaction_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transactionType = viewModel.getTransactionType()

        initViews()
        configureListeners()
        configureObservers()
    }

    private fun initViews() {
        when (transactionType) {
            TransactionType.EXPENSES -> {
                binding.title.text = resources.getString(R.string.add_expense_title)
            }

            TransactionType.INCOMES -> {
                binding.title.text = resources.getString(R.string.add_income_title)
            }

            null -> {}
        }

        val formattedDate =
            SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(selectedDate.time)
        binding.date.setText(formattedDate)

        val formattedTime =
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(selectedTime.time)
        binding.time.setText(formattedTime)

        // TODO get default or saved currency
        binding.currency.setText("RUB")
    }

    private fun configureListeners() {
        binding.done.setOnClickListener {
            val amount = binding.amount.text.toString()
            val category = binding.category.text.toString()

            if (!isRequiredFieldsFilled(amount, category)) {
                return@setOnClickListener
            }

            val comment = binding.comment.text.toString()

            viewModel.createTransaction(amount.toDouble(), comment)

            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.currency.setOnClickListener {
            findNavController().navigate(AddTransactionFragmentDirections.toCurrencyFragment())
        }

        binding.category.setOnClickListener {
            findNavController().navigate(AddTransactionFragmentDirections.toCategoryFragment())
        }

        binding.date.setOnClickListener {
            showDatePickerDialog()
        }

        binding.time.setOnClickListener {
            showTimePickerDialog()
        }

        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun configureObservers() {
        viewModel.categoryName.observe(viewLifecycleOwner) {
            binding.category.setText(it)
        }

        viewModel.currencyName.observe(viewLifecycleOwner) {
            binding.currency.setText(it)
        }
    }

    private fun isRequiredFieldsFilled(amount: String, category: String): Boolean {
        if (category.isBlank()) {
            Toast.makeText(
                requireContext(),
                R.string.add_transaction_empty_category_error,
                Toast.LENGTH_SHORT
            ).show()

            return false
        }

        if (amount.isBlank()) {
            Toast.makeText(
                requireContext(),
                R.string.add_transaction_empty_amount_error,
                Toast.LENGTH_SHORT
            ).show()

            return false
        }

        return true
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                selectedDate = calendar.time

                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val selectedDate = dateFormat.format(calendar.time)
                binding.date.setText(selectedDate)

                viewModel.updateDate(this.selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                selectedTime = calendar.time

                val formattedTime =
                    SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
                binding.time.setText(formattedTime)

                viewModel.updateTime(selectedTime)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }
}