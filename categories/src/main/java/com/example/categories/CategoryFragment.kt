package com.example.categories

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.categories.adapter.CategoryListAdapter
import com.example.categories.databinding.FragmentCategoryBinding
import com.example.categories.di.CategoriesComponent
import com.example.core_api.mediators.ExpensesTrackerApp
import kotlinx.parcelize.Parcelize

class CategoryFragment : Fragment() {
    private val binding by viewBinding(FragmentCategoryBinding::bind)

    private val adapter = CategoryListAdapter()

    private var categoryType: CategoryType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryType = arguments?.getParcelable(CATEGORY_TYPE_KEY)
    }

    override fun onAttach(context: Context) {
        CategoriesComponent.create(
            (requireActivity().application as ExpensesTrackerApp).getFacade()
        ).inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter

        adapter.onItemClickAction = { item ->
            // TODO choose this category
        }
        adapter.onRenameItemClicked = { item ->
            // TODO rename this item
        }
        adapter.onDeleteItemClicked = { item ->
            // TODO delete this item
        }

        adapter.submitList(getTestList())
    }

    // TODO test data
    private fun getTestList() =
        listOf(
            "Транспорт",
            "Продукты",
            "Еда",
            "Квартира",
            "Одежда",
            "Подарки",
            "Развлечения",
            "Разное",
        )

    companion object {
        private const val CATEGORY_TYPE_KEY = "categoryType"

        fun newInstance(categoryType: CategoryType): Fragment {
            val fragment = CategoryFragment()
            val args = Bundle().apply {
                putParcelable(CATEGORY_TYPE_KEY, categoryType)
            }
            fragment.arguments = args
            return fragment
        }
    }

    @Parcelize
    enum class CategoryType: Parcelable {
        EXPENSES, INCOMES
    }
}