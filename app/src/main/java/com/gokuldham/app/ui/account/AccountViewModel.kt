package com.gokuldham.app.ui.account

import com.gokuldham.app.ui.account.response.LogoutResponse
import com.gokuldham.app.data.local.AppPreference
import com.gokuldham.app.ui.base.BaseViewModel
import com.gokuldham.app.util.NetworkResponseCallback

class AccountViewModel : BaseViewModel<AccountNavigator>() {

    fun logoutApi() {
        navigator!!.showProgress()
        val map = HashMap<String, String>()
        disposable.add(
            LogoutResponse().doNetworkRequest(map,
                AppPreference,
                object : NetworkResponseCallback<LogoutResponse> {
                    override fun onResponse(data: LogoutResponse) {
                        navigator!!.hideProgress()

//                        if (data.isSuccess) {
//                            navigator!!.navigateToSignIn(data.message)
//                        } else {
//                            onServerError(data.message)
//                        }
                    }

                    override fun onFailure(message: String) {
                        navigator!!.hideProgress()
                        navigator!!.showNetworkError(message)
                    }

                    override fun onServerError(error: String) {
                        navigator!!.hideProgress()
                        //navigator!!.showNetworkError(error)
                        navigator!!.navigateToSignIn("");
                    }

                    override fun onSessionExpire(error: String) {
                        navigator!!.hideProgress()
                        navigator!!.showSessionExpireAlert(error)
                    }

                    override fun onAppVersionUpdate(msg: String) {
                        navigator!!.hideProgress()
                        //navigator!!.showAppVersionUpdate(msg)
                    }
                })
        )
    }

}