package com.gokuldham.app.ui.customer.customerlist

import com.gokuldham.app.data.ListData
import com.gokuldham.app.data.local.AppPreference
import com.gokuldham.app.databinding.DialogAddVillageBinding
import com.gokuldham.app.ui.base.BaseViewModel
import com.gokuldham.app.ui.customer.response.CustomerListResponse
import com.gokuldham.app.ui.village.response.AddVillageResponse
import com.gokuldham.app.util.NetworkResponseCallback

class CustomerListViewModel : BaseViewModel<CustomerListNavigator>() {

    var villageData: ListData? = null

    fun customerListAPI(searchKey: String) {

        val requestMap: HashMap<String, Any> = HashMap()
        requestMap["name"] = searchKey
        if (villageData != null) {
            requestMap["villageid"] = villageData?.Id.toString()
        } else {
            requestMap["villageid"] = ""
        }

        if (searchKey.isEmpty()) {
            navigator!!.showProgress()
        }

        disposable.add(
            CustomerListResponse().doNetworkRequest(
                requestMap, AppPreference,
                object : NetworkResponseCallback<CustomerListResponse> {

                    override fun onResponse(data: CustomerListResponse) {
                        navigator!!.hideProgress()
                        navigator!!.villageListResponse(data)
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
            AddVillageResponse().doNetworkRequest(requestMap, isUpdate,
                object : NetworkResponseCallback<AddVillageResponse> {

                    override fun onResponse(data: AddVillageResponse) {
                        navigator!!.hideProgress()
                        navigator!!.addVillageResponse(data)

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