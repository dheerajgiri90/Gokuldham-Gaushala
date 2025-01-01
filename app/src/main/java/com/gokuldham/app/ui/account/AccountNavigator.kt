package com.gokuldham.app.ui.account

import com.gokuldham.app.util.CommonNavigator

interface AccountNavigator : CommonNavigator {

    fun navigateToSignIn(message: String)
}