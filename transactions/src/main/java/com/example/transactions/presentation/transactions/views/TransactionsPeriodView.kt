package com.example.transactions.presentation.transactions.views

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.transactions.R
import com.example.transactions.databinding.TransactionsPeriodBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TransactionsPeriodView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding by viewBinding(TransactionsPeriodBinding::bind)

    private var currentPickedDate = Date()
    private var transactionPeriodType = TransactionPeriodType.DAY

    var onDateChanged: ((Date, TransactionPeriodType) -> Unit)? = null
    var onStartPeriodDateChanged: ((Date, TransactionPeriodType) -> Unit)? = null
    var onEndPeriodDateChanged: ((Date, TransactionPeriodType) -> Unit)? = null

    init {
        inflate(context, R.layout.transactions_period, this)

        configureListeners()
    }

    fun setPeriod(transactionPeriodType: TransactionPeriodType) {
        this.transactionPeriodType = transactionPeriodType

        val currentDate = Date()
        currentPickedDate = currentDate

        with(binding) {
            date.setText(resources.getString(R.string.add_expense_title))

            when (transactionPeriodType) {
                TransactionPeriodType.DAY -> setDay(currentDate)
                TransactionPeriodType.WEEK -> setWeek(currentDate)
                TransactionPeriodType.MONTH -> setMonth(currentDate)
                TransactionPeriodType.YEAR -> setYear(currentDate)
                TransactionPeriodType.PERIOD -> {
                    setStartPeriodDate(currentDate)
                    setEndPeriodDate(currentDate)
                }
                TransactionPeriodType.All -> {}
            }

            previousButton.isVisible = transactionPeriodType.isButtonsVisible
            nextButton.isVisible = transactionPeriodType.isButtonsVisible
            dateInputLayout.isVisible = transactionPeriodType.isDateInputLayoutVisible
            dateTextView.isVisible = transactionPeriodType.isDateTextViewVisible
            startDateInputLayout.isVisible = transactionPeriodType.isStartEndDateInputLayoutVisible
            endDateInputLayout.isVisible = transactionPeriodType.isStartEndDateInputLayoutVisible
            hyphen.isVisible = transactionPeriodType.isHyphenVisible
        }
    }

    private fun configureListeners() {
        binding.date.setOnClickListener {
            getDateFromDatePickerDialog { pickedDate ->
                currentPickedDate = pickedDate

                when (transactionPeriodType) {
                    TransactionPeriodType.DAY -> setDay(pickedDate)
                    TransactionPeriodType.WEEK -> setWeek(pickedDate)
                    TransactionPeriodType.MONTH -> setMonth(pickedDate)
                    TransactionPeriodType.YEAR -> setYear(pickedDate)
                    TransactionPeriodType.PERIOD -> {}
                    TransactionPeriodType.All -> {}
                }
            }
        }

        binding.previousButton.setOnClickListener {
            shiftPeriod(ChangePeriodType.PREVIOUS)
        }

        binding.nextButton.setOnClickListener {
            shiftPeriod(ChangePeriodType.NEXT)
        }

        binding.startDate.setOnClickListener {
            getDateFromDatePickerDialog { pickedDate ->
                setStartPeriodDate(pickedDate)
            }
        }

        binding.endDate.setOnClickListener {
            getDateFromDatePickerDialog { pickedDate ->
                setEndPeriodDate(pickedDate)
            }
        }
    }

    private fun setDay(date: Date) {
        val dateFormat = SimpleDateFormat(TransactionPeriodType.DAY.dateFormat, Locale(LOCALE))
        val formattedDate = dateFormat.format(date)

        binding.date.setText(formattedDate)
        onDateChanged?.invoke(date, TransactionPeriodType.DAY)
    }

    private fun setWeek(date: Date) {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.firstDayOfWeek = Calendar.MONDAY

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val firstDayOfWeek = calendar.time

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val lastDayOfWeek = calendar.time

        val dateFormat = SimpleDateFormat(TransactionPeriodType.WEEK.dateFormat, Locale(LOCALE))
        val yearFormat = SimpleDateFormat(TransactionPeriodType.YEAR.dateFormat, Locale(LOCALE))

        val formattedFirstDay = dateFormat.format(firstDayOfWeek)
        val formattedLastDay = dateFormat.format(lastDayOfWeek)
        val formattedYear = yearFormat.format(lastDayOfWeek)
        val formattedLastDayWithYear = "$formattedLastDay $formattedYear"
        val formattedDate = "$formattedFirstDay - $formattedLastDayWithYear"

        binding.date.setText(formattedDate)
            onDateChanged?.invoke(date, TransactionPeriodType.WEEK)
    }

    private fun setMonth(date: Date) {
        val dateFormat = SimpleDateFormat(TransactionPeriodType.MONTH.dateFormat, Locale(LOCALE))
        val formattedDate = dateFormat.format(date)

        binding.date.setText(formattedDate)
        onDateChanged?.invoke(date, TransactionPeriodType.MONTH)
    }

    private fun setYear(date: Date) {
        val dateFormat = SimpleDateFormat(TransactionPeriodType.YEAR.dateFormat, Locale(LOCALE))
        val formattedDate = dateFormat.format(date)

        binding.dateTextView.text = formattedDate
        onDateChanged?.invoke(date, TransactionPeriodType.YEAR)
    }

    private fun setStartPeriodDate(date: Date) {
        val dateFormat = SimpleDateFormat(TransactionPeriodType.PERIOD.dateFormat, Locale(LOCALE))
        val formattedDate = dateFormat.format(date)
        binding.startDate.setText(formattedDate)
        onStartPeriodDateChanged?.invoke(date, TransactionPeriodType.PERIOD)
    }

    private fun setEndPeriodDate(date: Date) {
        val dateFormat = SimpleDateFormat(TransactionPeriodType.PERIOD.dateFormat, Locale(LOCALE))
        val formattedDate = dateFormat.format(date)
        binding.endDate.setText(formattedDate)
        onEndPeriodDateChanged?.invoke(date, TransactionPeriodType.PERIOD)
    }

    private fun shiftPeriod(changeType: ChangePeriodType) {
        val calendar = Calendar.getInstance()
        calendar.time = currentPickedDate

        val calendarField: Int
        val setMethod: (Date) -> Unit

        when (transactionPeriodType) {
            TransactionPeriodType.DAY -> {
                calendarField = Calendar.DAY_OF_YEAR
                setMethod = ::setDay
            }

            TransactionPeriodType.WEEK -> {
                calendarField = Calendar.WEEK_OF_YEAR
                setMethod = ::setWeek
            }

            TransactionPeriodType.MONTH -> {
                calendarField = Calendar.MONTH
                setMethod = ::setMonth
            }

            TransactionPeriodType.YEAR -> {
                calendarField = Calendar.YEAR
                setMethod = ::setYear
            }

            TransactionPeriodType.PERIOD -> {
                calendarField = Calendar.YEAR
                setMethod = ::setYear
            }
            TransactionPeriodType.All -> {
                calendarField = Calendar.DAY_OF_YEAR
                setMethod = ::setDay
            }
        }

        when (changeType) {
            ChangePeriodType.NEXT -> { calendar.add(calendarField, +1) }
            ChangePeriodType.PREVIOUS -> { calendar.add(calendarField, -1) }
        }

        currentPickedDate = calendar.time
        setMethod(currentPickedDate)
    }

    private fun getDateFromDatePickerDialog(onDatePicked: (Date) -> Unit) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            binding.date.context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)

                onDatePicked(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private companion object {
        private const val LOCALE = "ru"
    }

    private enum class ChangePeriodType {
        PREVIOUS, NEXT
    }
}