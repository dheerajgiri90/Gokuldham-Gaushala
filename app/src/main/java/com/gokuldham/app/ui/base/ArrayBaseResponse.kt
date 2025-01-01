package com.gokuldham.app.ui.base

import com.gokuldham.app.util.ErrorBean
import com.gokuldham.app.util.NetworkResponseCallback
import com.google.gson.annotations.SerializedName
import io.reactivex.disposables.Disposable

abstract class ArrayBaseResponse<T, K, V> {

//    @SerializedName("success")
//    var isSuccess: Boolean = false

    @SerializedName("error")
    var errorBean: ErrorBean? = null

    abstract fun doNetworkRequestNew(
        requestParam: MutableList<HashMap<K, V>>,
        vararg: Any,
        networkResponseCallback: NetworkResponseCallback<T>
    ): Disposable

}
