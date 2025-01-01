package com.gokuldham.app.ui.dailyexpenses

import com.gokuldham.app.ui.dailyexpenses.response.AddExpensesResponse
import com.gokuldham.app.ui.dailyexpenses.response.ExpensesListResponse
import com.gokuldham.app.util.CommonNavigator


interface ExpensesListNavigator : CommonNavigator {

    fun expensesListResponse(response: ExpensesListResponse)

    fun addExpensesResponse(response: AddExpensesResponse)
}