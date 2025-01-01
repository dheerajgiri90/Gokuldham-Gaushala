package com.gokuldham.app.ui.mortgage.getmortgage

import com.gokuldham.app.ui.mortgage.response.MortgageListResponse
import com.gokuldham.app.util.CommonNavigator

interface MortgageListNavigator : CommonNavigator {

    fun mortgageListResponse(response: MortgageListResponse)
}