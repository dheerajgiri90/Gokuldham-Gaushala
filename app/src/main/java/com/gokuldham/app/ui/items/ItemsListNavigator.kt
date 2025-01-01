package com.gokuldham.app.ui.items

import com.gokuldham.app.ui.items.response.AddItemsResponse
import com.gokuldham.app.ui.items.response.ItemsListResponse
import com.gokuldham.app.util.CommonNavigator

interface ItemsListNavigator : CommonNavigator {

    fun itemListResponse(response: ItemsListResponse)

    fun addItemsResponse(response: AddItemsResponse)
}