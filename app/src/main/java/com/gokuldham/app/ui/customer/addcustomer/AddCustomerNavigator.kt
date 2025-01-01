package com.gokuldham.app.ui.customer.addcustomer

import com.gokuldham.app.ui.customer.response.AddCustomerResponse
import com.gokuldham.app.util.CommonNavigator

interface AddCustomerNavigator : CommonNavigator {

    fun addCustomerResponse(response: AddCustomerResponse)
}