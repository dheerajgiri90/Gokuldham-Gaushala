package com.gokuldham.app.ui.exchange.exchangelist

import com.gokuldham.app.ui.exchange.response.AddExchangeResponse
import com.gokuldham.app.ui.exchange.response.ExchangeListResponse
import com.gokuldham.app.util.CommonNavigator

interface ExchangeListNavigator : CommonNavigator {

    fun exchangeListResponse(response: ExchangeListResponse)

    fun addExchangeResponse(response: AddExchangeResponse)
}