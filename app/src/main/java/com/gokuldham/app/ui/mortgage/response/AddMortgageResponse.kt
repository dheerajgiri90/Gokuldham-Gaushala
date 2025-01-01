package com.gokuldham.app.ui.mortgage.response

import Api
import com.gokuldham.app.data.Parser
import com.gokuldham.app.data.remote.ApiFactory
import com.gokuldham.app.ui.base.ArrayBaseResponse
import com.gokuldham.app.util.Logger
import com.gokuldham.app.util.NetworkResponseCallback
import com.google.gson.annotations.SerializedName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AddMortgageResponse : ArrayBaseResponse<AddMortgageResponse, String, Any>() {

    @SerializedName("ResponseMessage")
    var message: String = ""

    @SerializedName("TokenNo")
    var tokenNumber: String = ""

    override fun doNetworkRequestNew(
        requestParam: MutableList<HashMap<String, Any>>,
        vararg: Any,
        networkResponseCallback: NetworkResponseCallback<AddMortgageResponse>
    ): Disposable {

        val api = ApiFactory.clientWithHeader.create(Api::class.java)
        Logger.e("requestParam>>>>>", "" + requestParam.toString())

        return api.addMortgage(requestParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { networkResponseCallback.onResponse(it) },
                { throwable -> Parser.parseErrorResponse(throwable, networkResponseCallback) })

    }

}
