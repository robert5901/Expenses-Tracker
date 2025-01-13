package com.example.transactions.data.mappers

import com.example.core_api.entity.ExpenseCategoryEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class TransactionsMapperTest {

    @Test
    fun `mapExpenseCategoryEntityListToCategoryList maps correctly`() = runBlocking {
        // Подготовка данных
        val mapper = TransactionsMapper()
        val expenseCategoryEntities = listOf(
            ExpenseCategoryEntity(categoryId = 1, name = "Food"),
            ExpenseCategoryEntity(categoryId = 2, name = "Transport")
        )

        // Вызов метода
        val categories = mapper.mapExpenseCategoryEntityListToCategoryList(expenseCategoryEntities)

        // Проверка результата
        assertEquals(2, categories.size)
        assertEquals("Food", categories[0].name)
        assertEquals("Transport", categories[1].name)
    }
}