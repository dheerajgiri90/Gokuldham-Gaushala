package com.gokuldham.app.ui.businessman

import com.gokuldham.app.data.local.AppPreference
import com.gokuldham.app.databinding.DialogAddVillageBinding
import com.gokuldham.app.ui.base.BaseViewModel
import com.gokuldham.app.ui.businessman.response.AddBusinessManResponse
import com.gokuldham.app.ui.businessman.response.BusinessManListResponse
import com.gokuldham.app.util.NetworkResponseCallback

class BusinessManViewModel : BaseViewModel<BusinessManNavigator>() {

    fun villageListAPI(searchKey: String) {

        val requestMap: HashMap<String, Any> = HashMap()
        requestMap["name"] = searchKey

        if (searchKey.isEmpty()) {
            navigator!!.showProgress()
        }

        disposable.add(
            BusinessManListResponse().doNetworkRequest(
                requestMap, AppPreference,
                object : NetworkResponseCallback<BusinessManListResponse> {

                    override fun onResponse(data: BusinessManListResponse) {
                        navigator!!.hideProgress()
                        navigator!!.businessManListResponse(data)
                    }

                    override fun onFailure(message: String) {
                        navigator!!.hideProgress()
                        navigator!!.showNetworkError(message)
                    }

                    override fun onServerError(error: String) {
                        navigator!!.hideProgress()
                        navigator!!.showNetworkError(error)
                    }

                    override fun onErrorMessage(errorMessage: String) {
                        navigator!!.hideProgress()
                        super.onErrorMessage(errorMessage)
                        navigator!!.showNetworkError(errorMessage, false)
                    }

                    override fun onSessionExpire(error: String) {
                        navigator!!.hideProgress()
                        navigator!!.showSessionExpireAlert(error)
                    }

                    override fun onAppVersionUpdate(msg: String) {
                        navigator!!.hideProgress()
                        navigator!!.showNetworkError(msg)
                    }

                })
        )
    }


    fun checkValidation(viewDataBinding: DialogAddVillageBinding): Boolean {
        if (viewDataBinding.editName.text!!.trim().isEmpty()) {
            navigator!!.showValidationError("कृपया नाम दर्ज करें")
            viewDataBinding.editName.requestFocus()
            return false
        }

        return true
    }

    private var isUpdate = false
    fun addVillageApi(viewDataBinding: DialogAddVillageBinding?, villageId: String?) {

        navigator!!.showProgress()
        val requestMap: HashMap<String, Any> = HashMap()
        requestMap["Name"] = viewDataBinding?.editName?.text.toString()
        if (villageId != null) {
            requestMap["Id"] = villageId
            isUpdate = true
        } else {
            isUpdate = false
        }

        disposable.add(
            AddBusinessManResponse().doNetworkRequest(requestMap, isUpdate,
                object : NetworkResponseCallback<AddBusinessManResponse> {

                    override fun onResponse(data: AddBusinessManResponse) {
                        navigator!!.hideProgress()
                        navigator!!.addBusinessManResponse(data)

                    }

                    override fun onFailure(message: String) {
                        navigator!!.hideProgress()
                        navigator!!.showNetworkError(message)
                    }

                    override fun onServerError(error: String) {
                        navigator!!.hideProgress()
                        navigator!!.showNetworkError(error)
                    }

                    override fun onErrorMessage(errorMessage: String) {
                        navigator!!.hideProgress()
                        super.onErrorMessage(errorMessage)
                        navigator!!.showNetworkError(errorMessage, false)
                    }

                    override fun onSessionExpire(error: String) {
                        navigator!!.hideProgress()
                        navigator!!.showSessionExpireAlert(error)
                    }

                    override fun onAppVersionUpdate(msg: String) {
                        navigator!!.hideProgress()
                        navigator!!.showNetworkError(msg)
                    }

                })
        )
    }

}