package com.gokuldham.app.ui.base

import com.gokuldham.app.util.ErrorBean
import com.gokuldham.app.util.NetworkResponseCallback
import com.google.gson.annotations.SerializedName
import io.reactivex.disposables.Disposable
import java.util.*

abstract class BaseResponse<T, K, V> {

//    @SerializedName("ResponseMessage")
//    var isSuccess: String = ""

    @SerializedName("error")
    var errorBean: ErrorBean? = null


    abstract fun doNetworkRequest(
        requestParam: HashMap<K, V>,
        vararg: Any,
        networkResponseCallback: NetworkResponseCallback<T>
    ): Disposable

}
