package com.gokuldham.app.ui.signup

import com.gokuldham.app.ui.signup.response.SignUpResponse
import com.gokuldham.app.util.CommonNavigator

interface SignupNavigator : CommonNavigator {
    fun onSignupClick()
    fun onSigninClick()
    fun signupSuccess(data: SignUpResponse)
}