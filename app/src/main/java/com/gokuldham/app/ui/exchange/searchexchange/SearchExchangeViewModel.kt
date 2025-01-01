package com.gokuldham.app.ui.exchange.searchexchange

import com.gokuldham.app.data.ListData
import com.gokuldham.app.data.local.AppPreference
import com.gokuldham.app.databinding.ActivitySearchExchangeBinding
import com.gokuldham.app.ui.base.BaseViewModel
import com.gokuldham.app.ui.customer.response.CustomerListResponse.CustomerData
import com.gokuldham.app.ui.mortgage.response.MortgageListResponse
import com.gokuldham.app.util.NetworkResponseCallback

class SearchExchangeViewModel : BaseViewModel<SearchExchangeNavigator>() {

    var villageData: ListData? = null
    var customerData: CustomerData? = null


    fun checkValidation(viewDataBinding: ActivitySearchExchangeBinding): Boolean {
        if (viewDataBinding.textTokenNumber.text!!.trim().isEmpty()) {
            navigator!!.showValidationError("कृपया टोकन नंबर दर्ज करें")
            viewDataBinding.textTokenNumber.requestFocus()
            return false
        }

        return true
    }

    fun getMortgageData(viewDataBinding: ActivitySearchExchangeBinding) {

        navigator!!.showProgress()

        val requestMap: HashMap<String, Any> = HashMap()

        requestMap["name"] = ""
        requestMap["villageid"] = ""
        requestMap["isclosed"] = false
        requestMap["isexchanged"] = ""
        requestMap["mortgageid"] = viewDataBinding.textTokenNumber.text.toString().trim()
        requestMap["customerid"] = ""


        disposable.add(
            MortgageListResponse().doNetworkRequest(requestMap,
                AppPreference,
                object : NetworkResponseCallback<MortgageListResponse> {

                    override fun onResponse(data: MortgageListResponse) {
                        navigator!!.hideProgress()
                        navigator!!.searchExchangeResponse(data)
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