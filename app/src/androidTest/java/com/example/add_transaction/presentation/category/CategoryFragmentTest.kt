package com.example.add_transaction.presentation.category

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.add_transaction.R
import com.example.add_transaction.presentation.addTransaction.AddTransactionFragment
import com.example.add_transaction.presentation.addTransaction.AddTransactionViewModel
import com.example.add_transaction.presentation.models.TransactionType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryFragmentTest {

    private lateinit var viewModel: AddTransactionViewModel

    @Before
    fun setUp() {
        viewModel = mockk()
        every { viewModel.getTransactionType() } returns TransactionType.EXPENSES
    }

    @Test
    fun testNavigationToCategoryFragment() {
        val scenario = launchFragmentInContainer<AddTransactionFragment>()
        val navController = mockk<NavController>(relaxed = true)

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.category)).perform(click())

        // TODO fix that
//        verify { navController.navigate(AddTransactionFragmentDirections.toCategoryFragment()) }
    }

    @Test
    fun testCategoryFragmentNavigation() {
        val scenario = launchFragmentInContainer<CategoryFragment>()

        val recyclerView = onView(withId(R.id.recyclerView))

        recyclerView.check(matches(isDisplayed()))

        onView(withId(R.id.back)).perform(click())

        onView(withId(R.id.add_transaction_main_container)).check(matches(isDisplayed()))
    }
}