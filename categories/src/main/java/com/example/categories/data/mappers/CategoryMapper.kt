package com.example.categories.data.mappers

import com.example.categories.presentation.models.Category
import com.example.core_api.entity.ExpenseCategoryEntity
import com.example.core_api.entity.IncomeCategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryMapper @Inject constructor() {

    fun mapExpenseCategoryEntityToCategory(
        expenseCategoryList: Flow<List<ExpenseCategoryEntity>>
    ): Flow<List<Category>> =
        expenseCategoryList.map { entityList ->
            entityList.map { expenseCategoryEntity ->
                Category(
                    categoryId = expenseCategoryEntity.categoryId,
                    name = expenseCategoryEntity.name
                )
            }
        }

    fun mapIncomeCategoryEntityToCategory(
        incomeCategoryList: Flow<List<IncomeCategoryEntity>>
    ): Flow<List<Category>> =
        incomeCategoryList.map { entityList ->
            entityList.map { incomeCategoryEntity ->
                Category(
                    categoryId = incomeCategoryEntity.categoryId,
                    name = incomeCategoryEntity.name
                )
            }
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