package com.gokuldham.app.ui.businessman

import com.gokuldham.app.ui.businessman.response.AddBusinessManResponse
import com.gokuldham.app.ui.businessman.response.BusinessManListResponse
import com.gokuldham.app.util.CommonNavigator

interface BusinessManNavigator : CommonNavigator {

    fun businessManListResponse(response: BusinessManListResponse)

    fun addBusinessManResponse(response: AddBusinessManResponse)
}