package com.example.expensestracker.pieChart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.expensestracker.R
import com.example.expensestracker.databinding.FragmentPieChartBinding
//import com.example.expensestracker.pieChart.adapters.PieChartCategoriesAdapter
import com.example.expensestracker.pieChart.models.PieChartCategoriesDataUi
import com.example.expensestracker.pieChart.models.PieChartDataUi
import java.math.BigDecimal
import java.math.RoundingMode

class PieChartFragment : Fragment() {

    private val binding by viewBinding(FragmentPieChartBinding::bind)
    // TODO pass FinanceCategoryType in args
    private val argsType = FinanceCategoryType.EXPENSES
    private val argsPeriod = "Все время"

//    private val adapter = PieChartCategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pie_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.financeCategory.text = when (argsType) {
            FinanceCategoryType.EXPENSES -> {
                resources.getString(R.string.pie_chart_finance_category_expenses)
            }

            FinanceCategoryType.INCOMES -> {
                resources.getString(R.string.pie_chart_finance_category_incomes)
            }
        }

        binding.period.text = argsPeriod
        binding.pieChart.setPieData(getTestList())

        initRecyclerView()
    }

    private fun initRecyclerView() {
//        binding.recyclerView.adapter = adapter

//        adapter.onItemClicked = {
//            // TODO
//        }

//        adapter.setItems(getTestListForRecycler())
    }

    // TODO test data
    private fun getTestList(): List<PieChartDataUi> {
        return listOf(
            PieChartDataUi(
                category = "Продукты",
                amount = 49856.0
            ),
            PieChartDataUi(
                category = "Разное",
                amount = 40992.0
            ),
            PieChartDataUi(
                category = "Квартира",
                amount = 32770.0
            ),
            PieChartDataUi(
                category = "Транспорт",
                amount = 18729.0
            )
        )
    }

    private fun getTestListForRecycler(): List<PieChartCategoriesDataUi> {
        val pieChartData = getTestList()
        val totalAmount = pieChartData.sumOf { it.amount }

        val pieChartCategoriesData = pieChartData.map {
            PieChartCategoriesDataUi(
                amount = it.amount,
                category = it.category,
                percent = BigDecimal((it.amount / totalAmount) * 100)
                    .setScale(2, RoundingMode.HALF_UP)
                    .toDouble()
            )
        }

        return pieChartCategoriesData
    }
}

enum class FinanceCategoryType {
    EXPENSES,
    INCOMES
}

// core api. делаем интерфейсы для network, DB(Entity - DTO нужные во всей приле)
// core impl. реализация интерфейсов network, DB. Dagger components