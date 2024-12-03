package com.example.add_transaction

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.add_transaction.databinding.AddTransactionLayoutBinding
import com.example.add_transaction.di.AddTransactionComponent
import com.example.core_api.mediators.CategoryScreenNavigator
import com.example.core_api.mediators.ExpensesTrackerApp
import kotlinx.parcelize.Parcelize
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import com.example.core_api.R as core_R

class AddTransactionFragment : Fragment() {
    private val binding by viewBinding(AddTransactionLayoutBinding::bind)

    @Inject
    lateinit var categoryScreenNavigator: CategoryScreenNavigator

    private var addTransactionType: AddTransactionType? = null

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

        binding.title.text = resources.getString(R.string.add_expense_title)

        binding.category.setOnClickListener {
            when (addTransactionType) {
                AddTransactionType.EXPENSES -> {
                    categoryScreenNavigator.startExpensesCategoryScreen(
                        core_R.id.main_container,
                        parentFragmentManager
                    )
                }

                AddTransactionType.INCOMES -> {
                    categoryScreenNavigator.startIncomesCategoryScreen(
                        core_R.id.main_container,
                        parentFragmentManager
                    )
                }

                null -> {}
            }
        }

        binding.date.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    // TODO format date
                    val selectedDate = "$dayOfMonth.${month + 1}.$year"
                    binding.date.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        binding.time.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    // TODO format time
                    val selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
                    binding.time.setText(selectedTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        }
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