package com.gokuldham.app.ui.account.response

import Api
import com.gokuldham.app.data.Parser
import com.gokuldham.app.data.remote.ApiFactory
import com.gokuldham.app.ui.base.BaseResponse
import com.gokuldham.app.util.NetworkResponseCallback
import com.google.gson.annotations.SerializedName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LogoutResponse : BaseResponse<LogoutResponse, String, String>() {

    @SerializedName("message")
    var message: String = ""

    override fun doNetworkRequest(
        requestParam: HashMap<String, String>,
        vararg: Any,
        networkResponseCallback: NetworkResponseCallback<LogoutResponse>
    ): Disposable {
        val api = ApiFactory.clientWithHeader.create(Api::class.java)
        return api.logOut()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ networkResponseCallback.onResponse(it) },
                { throwable -> Parser.parseErrorResponse(throwable, networkResponseCallback) })
    }
}