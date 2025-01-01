package com.gokuldham.app.ui.village

import com.gokuldham.app.ui.village.response.AddVillageResponse
import com.gokuldham.app.ui.village.response.VillageListResponse
import com.gokuldham.app.util.CommonNavigator

interface VillageListNavigator : CommonNavigator {

    fun villageListResponse(response: VillageListResponse)
//
    fun addVillageResponse(response: AddVillageResponse)
}