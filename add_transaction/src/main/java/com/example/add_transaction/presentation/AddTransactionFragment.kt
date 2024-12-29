package com.example.add_transaction.presentation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.add_transaction.R
import com.example.add_transaction.databinding.AddTransactionLayoutBinding
import com.example.add_transaction.di.AddTransactionComponent
import com.example.add_transaction.navigation.viewModel.AddTransactionViewModel
import com.example.add_transaction.presentation.models.Transaction
import com.example.core_api.mediators.CategoryScreenNavigator
import com.example.core_api.mediators.ExpensesTrackerApp
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import com.example.core_api.R as core_R

class AddTransactionFragment : Fragment() {
    private val binding by viewBinding(AddTransactionLayoutBinding::bind)

    @Inject
    lateinit var categoryScreenNavigator: CategoryScreenNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AddTransactionViewModel by viewModels {
        viewModelFactory
    }

    private var addTransactionType: AddTransactionType? = null
    private var selectedDate = Date()
    private var selectedTime = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addTransactionType = arguments?.getParcelable(ADD_TRANSACTION_TYPE_KEY)
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
        return inflater.inflate(R.layout.add_transaction_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (addTransactionType) {
            AddTransactionType.EXPENSES -> {
               viewModel.createDefaultExpense(createDefaultTransaction())
            }

            AddTransactionType.INCOMES -> {
                viewModel.createDefaultIncome(createDefaultTransaction())
            }

            null -> {}
        }

        initViews()

        configureListeners()
        configureObservers()
    }

    private fun initViews() {
        when (addTransactionType) {
            AddTransactionType.EXPENSES -> {
                binding.title.text = resources.getString(R.string.add_expense_title)
            }

            AddTransactionType.INCOMES -> {
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
    }

    private fun configureListeners() {
        binding.done.setOnClickListener {
            createTransaction()
        }

        binding.category.setOnClickListener {
            viewModel.getDefaultExpenseId()
        }

        binding.date.setOnClickListener {
            showDatePickerDialog()
        }

        binding.time.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun configureObservers() {
        viewModel.defaultTransactionId.observe(viewLifecycleOwner) { id ->
            navigateToCategoryScreen(id)
        }
    }

    private fun createDefaultTransaction(): Transaction {
        return Transaction(
            date = Date(),
            categoryId = -1,
            amount = 0.0,
            // TODO set currency
            currency = "RUB",
            comment = ""
        )
    }

    private fun createTransaction() {
        if (binding.amount.text.toString().isEmpty()) {
            Toast.makeText(
                requireContext(),
                R.string.add_transaction_empty_amount_error,
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val calendarDate = Calendar.getInstance().apply {
            time = selectedDate
        }
        val calendarTime = Calendar.getInstance().apply {
            time = selectedTime
        }

        calendarDate.set(Calendar.HOUR_OF_DAY, calendarTime.get(Calendar.HOUR_OF_DAY))
        calendarDate.set(Calendar.MINUTE, calendarTime.get(Calendar.MINUTE))

        val transaction = Transaction(
            date = calendarDate.time,
            // TODO get picked category from CategoryFragment
            categoryId = 0L,
            amount = binding.amount.text.toString().toDouble(),
            // TODO get picked currency
            currency = "RUB",
            comment = binding.comment.text.toString()
        )

//        when (addTransactionType) {
//            AddTransactionType.EXPENSES -> {
//                viewModel.createExpense(transaction)
//            }
//
//            AddTransactionType.INCOMES -> {
//                viewModel.createIncome(transaction)
//            }
//
//            null -> {}
//        }
    }

    private fun navigateToCategoryScreen(transactionId: Long) {
        when (addTransactionType) {
            AddTransactionType.EXPENSES -> {
                categoryScreenNavigator.startExpensesCategoryScreen(
                    core_R.id.main_container,
                    parentFragmentManager,
                    transactionId
                )
            }

            AddTransactionType.INCOMES -> {
                categoryScreenNavigator.startIncomesCategoryScreen(
                    core_R.id.main_container,
                    parentFragmentManager,
                    transactionId
                )
            }

            null -> {}
        }
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
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }

    companion object {
        private const val ADD_TRANSACTION_TYPE_KEY = "AddTransactionType"

        fun newInstance(addTransactionType: AddTransactionType): AddTransactionFragment {
            val fragment = AddTransactionFragment()
            val args = Bundle().apply {
                putParcelable(ADD_TRANSACTION_TYPE_KEY, addTransactionType)
            }
            fragment.arguments = args
            return fragment
        }
    }

    @Parcelize
    enum class AddTransactionType : Parcelable {
        EXPENSES, INCOMES
    }
}