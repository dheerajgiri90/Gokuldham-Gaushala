package com.gokuldham.app.ui.exchange.response

import Api
import com.gokuldham.app.data.Parser
import com.gokuldham.app.data.remote.ApiFactory
import com.gokuldham.app.ui.base.BaseResponse
import com.gokuldham.app.util.Logger
import com.gokuldham.app.util.NetworkResponseCallback
import com.google.gson.annotations.SerializedName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AddExchangeItemResponse : BaseResponse<AddExchangeItemResponse, String, Any>() {

    @SerializedName("ResponseMessage")
    var message: String = ""

    override fun doNetworkRequest(
        requestParam: HashMap<String, Any>,
        vararg: Any,
        networkResponseCallback: NetworkResponseCallback<AddExchangeItemResponse>
    ): Disposable {

        val api = ApiFactory.clientWithHeader.create(Api::class.java)
        Logger.e("requestParam>>>>>", "" + requestParam.toString())

        return api.addExchangeItem(requestParam).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { networkResponseCallback.onResponse(it) },
                { throwable -> Parser.parseErrorResponse(throwable, networkResponseCallback) })
    }


}

