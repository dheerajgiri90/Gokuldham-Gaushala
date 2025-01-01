package com.gokuldham.app.ui.login

import com.gokuldham.app.ui.login.response.LoginResponse
import com.gokuldham.app.util.CommonNavigator

interface LoginNavigator : CommonNavigator {

    fun usedInstead()
    fun onClickSignup()
    fun onclickForgotPassword()
    fun loginSuccess(data: LoginResponse)

}