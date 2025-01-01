package com.gokuldham.app.ui.mortgage.mortgagedetail

import com.gokuldham.app.ui.mortgage.response.UpdateMortgageResponse
import com.gokuldham.app.util.CommonNavigator

interface MortgageDetailNavigator : CommonNavigator {

    fun updateMortgage(response: UpdateMortgageResponse, isClose: Boolean)

}