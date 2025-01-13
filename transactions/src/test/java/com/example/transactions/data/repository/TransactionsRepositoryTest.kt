package com.example.transactions.data.repository

import com.example.core_api.database.dao.ExpenseCategoryDao
import com.example.core_api.database.dao.ExpensesDao
import com.example.core_api.database.dao.IncomeCategoryDao
import com.example.core_api.database.dao.IncomesDao
import com.example.core_api.entity.ExpenseCategoryEntity
import com.example.core_api.entity.ExpenseEntity
import com.example.core_api.entity.IncomeCategoryEntity
import com.example.core_api.entity.IncomeEntity
import com.example.transactions.data.mappers.TransactionsMapper
import com.example.transactions.data.repository.TransactionsRepository
import com.example.transactions.presentation.models.Category
import com.example.transactions.presentation.models.Transaction
import com.example.transactions.presentation.models.TransactionType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TransactionsRepositoryTest {

    private lateinit var transactionsRepository: TransactionsRepository
    private val expenseDao: ExpensesDao = mockk()
    private val incomeDao: IncomesDao = mockk()
    private val expenseCategoryDao: ExpenseCategoryDao = mockk()
    private val incomeCategoryDao: IncomeCategoryDao = mockk()
    private val transactionsMapper: TransactionsMapper = mockk()

    @Before
    fun setUp() {
        transactionsRepository = TransactionsRepository(
            expenseDao,
            incomeDao,
            expenseCategoryDao,
            incomeCategoryDao,
            transactionsMapper
        )
    }

    @Test
    fun `test getCategoryList for EXPENSES`() = runBlocking {
        val mockCategoryList = listOf(
            Category(categoryId = 1L, name = "Food"),
            Category(categoryId = 2L, name = "Transport")
        )

        every { expenseCategoryDao.getExpenseCategoryList() } returns flowOf(
            listOf(
                mockk<ExpenseCategoryEntity>().apply {
                    every { categoryId } returns 1L
                    every { name } returns "Food"
                },
                mockk<ExpenseCategoryEntity>().apply {
                    every { categoryId } returns 2L
                    every { name } returns "Transport"
                }
            )
        )
        every { transactionsMapper.mapExpenseCategoryEntityListToCategoryList(any()) } returns mockCategoryList

        val result = transactionsRepository.getCategoryList(TransactionType.EXPENSES).toList()
        assertEquals(mockCategoryList, result[0])
        verify { expenseCategoryDao.getExpenseCategoryList() }
    }

    @Test
    fun `test getCategoryList for INCOMES`() = runBlocking {
        val mockCategoryList = listOf(
            Category(categoryId = 1L, name = "Salary"),
            Category(categoryId = 2L, name = "Freelance")
        )

        every { incomeCategoryDao.getIncomeCategoryList() } returns flowOf(
            listOf(
                mockk<IncomeCategoryEntity>().apply {
                    every { categoryId } returns 1L
                    every { name } returns "Salary"
                },
                mockk<IncomeCategoryEntity>().apply {
                    every { categoryId } returns 2L
                    every { name } returns "Freelance"
                }
            )
        )
        every { transactionsMapper.mapIncomeCategoryEntityListToCategoryList(any()) } returns mockCategoryList

        val result = transactionsRepository.getCategoryList(TransactionType.INCOMES).toList()
        assertEquals(mockCategoryList, result[0])
        verify { incomeCategoryDao.getIncomeCategoryList() }
    }

    @Test
    fun `test getTransactionList for EXPENSES`() = runBlocking {
        val mockTransactionList = listOf(
            Transaction(
                date = mockk(),
                time = mockk(),
                categoryId = 1L,
                amount = 100.0,
                currency = "USD",
                comment = "Lunch"
            )
        )

        every { expenseDao.getExpenseList() } returns flowOf(
            listOf(
                mockk<ExpenseEntity>().apply {
                    every { categoryId } returns 1L
                    every { amount } returns 100.0
                    every { currency } returns "USD"
                    every { comment } returns "Lunch"
                }
            )
        )
        every { transactionsMapper.mapExpenseListToTransactionList(any()) } returns mockTransactionList

        val result = transactionsRepository.getTransactionList(TransactionType.EXPENSES).toList()
        assertEquals(mockTransactionList, result[0])
        verify { expenseDao.getExpenseList() }
    }

    @Test
    fun `test getTransactionList for INCOMES`() = runBlocking {
        val mockTransactionList = listOf(
            Transaction(
                date = mockk(),
                time = mockk(),
                categoryId = 1L,
                amount = 1000.0,
                currency = "USD",
                comment = "Salary"
            )
        )

        every { incomeDao.getIncomeList() } returns flowOf(
            listOf(
                mockk<IncomeEntity>().apply {
                    every { categoryId } returns 1L
                    every { amount } returns 1000.0
                    every { currency } returns "USD"
                    every { comment } returns "Salary"
                }
            )
        )
        every { transactionsMapper.mapIncomeListToTransactionList(any()) } returns mockTransactionList

        val result = transactionsRepository.getTransactionList(TransactionType.INCOMES).toList()
        assertEquals(mockTransactionList, result[0])
        verify { incomeDao.getIncomeList() }
    }
}