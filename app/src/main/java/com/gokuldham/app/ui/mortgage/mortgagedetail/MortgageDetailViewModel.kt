package com.gokuldham.app.ui.mortgage.mortgagedetail

import com.gokuldham.app.data.ListData
import com.gokuldham.app.data.local.AppPreference
import com.gokuldham.app.ui.base.BaseViewModel
import com.gokuldham.app.ui.mortgage.response.MortgageListResponse.MortgageData
import com.gokuldham.app.ui.mortgage.response.UpdateMortgageResponse
import com.gokuldham.app.util.NetworkResponseCallback

class MortgageDetailViewModel : BaseViewModel<MortgageDetailNavigator>() {

    var villageData: ListData? = null
    var mortgageData: MortgageData? = null

    var endDateServer = "";
    var interestRate = "";

    fun updateMortgageApi(isClosed: Boolean) {

        navigator!!.showProgress()
        val requestMap: HashMap<String, Any> = HashMap()
        requestMap["Id"] = mortgageData?.Id.toString()
        requestMap["IsUpdate"] = false
        requestMap["IsClose"] = isClosed
        requestMap["IsCalculate"] = !isClosed
        requestMap["InterestRate"] = interestRate
        requestMap["EndDate"] = endDateServer

        disposable.add(
            UpdateMortgageResponse().doNetworkRequest(requestMap, AppPreference,
                object : NetworkResponseCallback<UpdateMortgageResponse> {

                    override fun onResponse(data: UpdateMortgageResponse) {
                        navigator!!.hideProgress()
                        navigator!!.updateMortgage(data, isClosed)

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