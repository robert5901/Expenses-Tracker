package com.example.add_transaction.presentation.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.add_transaction.R
import com.example.add_transaction.databinding.FragmentCategoryBinding
import com.example.add_transaction.di.AddTransactionComponent
import com.example.add_transaction.di.CategoryFactory
import com.example.add_transaction.presentation.addTransaction.AddTransactionViewModel
import com.example.add_transaction.presentation.category.adapter.CategoryListAdapter
import com.example.add_transaction.presentation.models.Category
import com.example.add_transaction.presentation.models.TransactionType
import com.example.core_api.mediators.ExpensesTrackerApp
import javax.inject.Inject

class CategoryFragment: Fragment() {

    private val binding by viewBinding(FragmentCategoryBinding::bind)

    @Inject
    @CategoryFactory
    lateinit var categoryViewModelFactory: ViewModelProvider.Factory

    private val categoryViewModel: CategoryViewModel by viewModels {
        categoryViewModelFactory
    }

    private val addTransactionMainViewModel: AddTransactionViewModel by viewModels(
        ownerProducer = {
            requireParentFragment().requireParentFragment()
        }
    )

    private val adapter = CategoryListAdapter()

    private var transactionType: TransactionType? = null

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
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        transactionType = addTransactionMainViewModel.getTransactionType()

        transactionType?.let { categoryViewModel.loadCategoryList(it) }

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

        adapter.onItemClickAction = { category ->
            addTransactionMainViewModel.updateCategoryId(category)
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        adapter.onRenameItemClicked = { category ->
            showRenameDialog(category)
        }
        adapter.onDeleteItemClicked = { categoryId ->
            showDeleteDialog(categoryId)
        }
    }

    private fun configureListeners() {
        with(binding) {
            newCategoryButton.setOnClickListener {
                val categoryName = newCategoryEditText.text.toString()

                if (categoryName.isBlank()) {
                    android.widget.Toast.makeText(
                        requireContext(),
                        resources.getString(R.string.category_empty_category_name_error),
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                transactionType?.let {
                    categoryViewModel.saveCategory(it, categoryName)
                }

                newCategoryEditText.text?.clear()
            }

            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initObservables() {
        categoryViewModel.categoryList.observe(viewLifecycleOwner) { categories ->
            if (categories.isNotEmpty()) {
                adapter.submitList(categories)
            }
        }
    }

    private fun showRenameDialog(category: Category) {
        val editText = EditText(requireContext())
        editText.setText(category.name)

        AlertDialog.Builder(requireContext())
            .setTitle(resources.getString(R.string.category_rename_category_alert_title))
            .setView(editText)
            .setPositiveButton(resources.getString(R.string.common_ok)) { dialog, _ ->
                if (editText.text.toString().isNotBlank()
                    && editText.text.toString() != category.name
                ) {
                    renameCategory(editText.text.toString(), category)
                }
                dialog.dismiss()
            }
            .setNegativeButton(resources.getString(R.string.common_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showDeleteDialog(categoryId: Long) {
        AlertDialog.Builder(requireContext())
            .setTitle(resources.getString(R.string.category_delete_category_alert_title))
            .setMessage(resources.getString(R.string.category_delete_category_alert_message))
            .setNegativeButton(resources.getString(R.string.common_no)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.common_yes)) { dialog, _ ->
                transactionType?.let {
                    categoryViewModel.deleteCategory(it, categoryId)
                }
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun renameCategory(newName: String, category: Category) {
        val newCategory = category.copy(name = newName)

        transactionType?.let {
            categoryViewModel.renameCategory(it, newCategory)
        }
    }
}