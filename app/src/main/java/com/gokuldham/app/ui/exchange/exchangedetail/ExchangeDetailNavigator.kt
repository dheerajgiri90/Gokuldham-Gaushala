package com.gokuldham.app.ui.exchange.exchangedetail

import com.gokuldham.app.ui.exchange.response.AddExchangeItemResponse
import com.gokuldham.app.ui.exchange.response.CloseExchangeResponse
import com.gokuldham.app.ui.exchange.response.ExchangeItemListResponse
import com.gokuldham.app.util.CommonNavigator

interface ExchangeDetailNavigator : CommonNavigator {

    fun exchangeItemsListResponse(response: ExchangeItemListResponse)

    fun addExchangeItemResponse(response: AddExchangeItemResponse)

    fun closeExchangeItemResponse(response: CloseExchangeResponse)
}