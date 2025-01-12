package com.example.add_transaction.data.mappers

import com.example.add_transaction.presentation.models.Category
import com.example.core_api.entity.ExpenseCategoryEntity
import com.example.core_api.entity.IncomeCategoryEntity
import javax.inject.Inject

class CategoryMapper @Inject constructor() {

    fun mapExpenseCategoryEntityToCategory(
        expenseCategoryList: List<ExpenseCategoryEntity>
    ): List<Category> =
        expenseCategoryList.map { entity ->
            Category(
                categoryId = entity.categoryId,
                name = entity.name
            )
        }

    fun mapIncomeCategoryEntityToCategory(
        incomeCategoryList: List<IncomeCategoryEntity>
    ): List<Category> =
        incomeCategoryList.map { entity ->
            Category(
                categoryId = entity.categoryId,
                name = entity.name
            )
        }

    fun mapToExpenseCategoryEntity(category: Category): ExpenseCategoryEntity =
        ExpenseCategoryEntity(
            categoryId = category.categoryId,
            name = category.name
        )

    fun mapToIncomeCategoryEntity(category: Category): IncomeCategoryEntity =
        IncomeCategoryEntity(
            categoryId = category.categoryId,
            name = category.name
        )
}