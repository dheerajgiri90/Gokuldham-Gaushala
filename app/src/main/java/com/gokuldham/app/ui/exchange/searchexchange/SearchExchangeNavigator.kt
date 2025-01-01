package com.gokuldham.app.ui.exchange.searchexchange

import com.gokuldham.app.ui.mortgage.response.MortgageListResponse
import com.gokuldham.app.util.CommonNavigator

interface SearchExchangeNavigator : CommonNavigator {

    fun searchExchangeResponse(response: MortgageListResponse)
}