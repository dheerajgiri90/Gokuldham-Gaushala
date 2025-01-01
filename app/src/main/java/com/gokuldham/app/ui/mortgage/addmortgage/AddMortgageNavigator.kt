package com.gokuldham.app.ui.mortgage.addmortgage

import com.gokuldham.app.ui.mortgage.response.AddMortgageResponse
import com.gokuldham.app.util.CommonNavigator

interface AddMortgageNavigator : CommonNavigator {

    fun addMortgageResponse(response: AddMortgageResponse)
}