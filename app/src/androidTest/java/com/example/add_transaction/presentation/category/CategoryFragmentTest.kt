package com.example.add_transaction.presentation.category

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.general.R
import com.example.main.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.core_api.R as core_R
import com.example.transactions.R as transactionsR

@RunWith(AndroidJUnit4::class)
class CategoryFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testGeneralFragmentIsDisplayed() {
        // Проверка отображаения main_container
        onView(withId(core_R.id.main_container)).check(matches(isDisplayed()))

        // Проверка отображаения GeneralFragment
        onView(withId(R.id.expenses)).check(matches(isDisplayed()))
    }

    @Test
    fun testTransactionsFragmentIsDisplayed() {
        // Нажатие на кнопку expenses_button
        onView(withId(R.id.expenses_button)).perform(click())

        // Проверка отображаения TransactionsFragment
        onView(
            allOf(
                withId(transactionsR.id.transaction_list_main_container),
                withParent(withId(core_R.id.main_container))
            )
        ).check(matches(isDisplayed()))

        // Проверка отображаения RecyclerView
        onView(withId(transactionsR.id.recyclerView)).check(matches(isDisplayed()))

        // Проверяем, что в RecyclerView есть элементы
        onView(withId(transactionsR.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
        onView(withId(transactionsR.id.recyclerView)).check(matches(hasDescendant(withText("test"))))
    }
}