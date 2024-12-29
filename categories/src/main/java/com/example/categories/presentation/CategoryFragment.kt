package com.example.categories.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.categories.R
import com.example.categories.presentation.adapter.CategoryListAdapter
import com.example.categories.databinding.FragmentCategoryBinding
import com.example.categories.di.CategoriesComponent
import com.example.categories.presentation.models.Category
import com.example.categories.presentation.viewModel.CategoryViewModel
import com.example.core_api.mediators.ExpensesTrackerApp
import javax.inject.Inject

class CategoryFragment : Fragment() {

    private val binding by viewBinding(FragmentCategoryBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CategoryViewModel by viewModels {
        viewModelFactory
    }

    private val adapter = CategoryListAdapter()

    private var categoryType: CategoryType? = null
    private var transactionId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryType = arguments?.getParcelable(CATEGORY_TYPE_KEY)
        transactionId = arguments?.getLong(TRANSACTION_ID_KEY)

        when (categoryType) {
            CategoryType.EXPENSES -> {
                viewModel.loadExpenseCategoryList()
            }

            CategoryType.INCOMES -> {
                viewModel.loadIncomeCategoryList()
            }

            null -> {}
        }
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
        configureListeners()
        initObservables()
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter

        adapter.onItemClickAction = { categoryId ->
            val transactionId = transactionId

            if (transactionId != null) {
                when (categoryType) {
                    CategoryType.EXPENSES -> {
                        viewModel.updateExpenseCategoryId(transactionId, categoryId)
                    }

                    CategoryType.INCOMES -> {
                        viewModel.updateIncomeCategoryId(transactionId, categoryId)
                    }

                    null -> {}
                }
            }

            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        adapter.onRenameItemClicked = { category ->
            showRenameDialog(category)
        }
        adapter.onDeleteItemClicked = { category ->
            when (categoryType) {
                CategoryType.EXPENSES -> {
                    viewModel.deleteExpenseCategory(category.categoryId)
                }

                CategoryType.INCOMES -> {
                    viewModel.deleteIncomeCategory(category.categoryId)
                }

                null -> {}
            }
        }
    }

    private fun configureListeners() {
        with(binding) {
            newCategoryButton.setOnClickListener {
                val categoryName = newCategoryEditText.text.toString()

                if (categoryName.isBlank()) {
                    Toast.makeText(
                        requireContext(),
                        resources.getString(R.string.category_empty_category_name_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                when (categoryType) {
                    CategoryType.EXPENSES -> {
                        viewModel.saveExpenseCategory(categoryName)
                    }

                    CategoryType.INCOMES -> {
                        viewModel.saveIncomeCategory(categoryName)
                    }

                    null -> {}
                }

                newCategoryEditText.text?.clear()
            }

            back.setOnClickListener {
                // TODO go back
            }
        }
    }

    private fun initObservables() {
        viewModel.expenseCategoryList.observe(viewLifecycleOwner) { categories ->
            if (categories.isNotEmpty()) {
                adapter.submitList(categories)
            }
        }

        viewModel.incomeCategoryList.observe(viewLifecycleOwner) { categories ->
            if (categories.isNotEmpty()) {
                adapter.submitList(categories)
            }
        }
    }

    private fun showRenameDialog(category: Category) {
        val editText = EditText(requireContext())
        editText.setText(category.name)

        AlertDialog.Builder(requireContext())
            .setTitle("Изменение названия")
            .setView(editText)
            .setPositiveButton("Ок") { dialog, _ ->
                if (editText.text.toString().isNotBlank()
                    && editText.text.toString() != category.name
                ) {
                    renameCategory(editText.text.toString(), category)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun renameCategory(newName: String, category: Category) {
        val newCategory = category.copy(name = newName)

        when (categoryType) {
            CategoryType.EXPENSES -> {
                viewModel.renameExpenseCategory(newCategory)
            }

            CategoryType.INCOMES -> {
                viewModel.renameIncomeCategory(newCategory)
            }

            null -> {}
        }
    }

    companion object {
        private const val CATEGORY_TYPE_KEY = "categoryType"
        private const val TRANSACTION_ID_KEY = "transactionId"

        fun newInstance(categoryType: CategoryType, transactionId: Long): Fragment {
            val fragment = CategoryFragment()
            val args = Bundle().apply {
                putParcelable(CATEGORY_TYPE_KEY, categoryType)
                putLong(TRANSACTION_ID_KEY, transactionId)
            }
            fragment.arguments = args
            return fragment
        }
    }
}