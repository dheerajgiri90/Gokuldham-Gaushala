package com.gokuldham.app.ui.customer.customerlist

import com.gokuldham.app.ui.customer.response.CustomerListResponse
import com.gokuldham.app.ui.village.response.AddVillageResponse
import com.gokuldham.app.util.CommonNavigator

interface CustomerListNavigator : CommonNavigator {

    fun villageListResponse(response: CustomerListResponse)
//
    fun addVillageResponse(response: AddVillageResponse)
}