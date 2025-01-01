package com.gokuldham.app.ui.mortgage.mortgageoption

import android.os.Bundle
import com.gokuldham.app.BR
import com.gokuldham.app.R
import com.gokuldham.app.databinding.ActivityMortgageOptionBinding
import com.gokuldham.app.ui.base.BaseActivity
import com.gokuldham.app.ui.businessman.BusinessManActivity
import com.gokuldham.app.ui.customer.customerlist.CustomerListActivity
import com.gokuldham.app.ui.dailyexpenses.ExpensesListActivity
import com.gokuldham.app.ui.exchange.searchexchange.SearchExchangeActivity
import com.gokuldham.app.ui.items.ItemsListActivity
import com.gokuldham.app.ui.mortgage.addmortgage.AddMortgageActivity
import com.gokuldham.app.util.ActivityNavigator
import com.gokuldham.app.util.DataBinding

class MortgageOptionActivity :
    BaseActivity<ActivityMortgageOptionBinding, MortgageOptionViewModel>(),
    MortgageOptionNavigator {

    override val bindingVariable: Int
        get() = BR.mortgageOptionViewModel

    override val layoutId: Int
        get() = R.layout.activity_mortgage_option

    override val viewModel = MortgageOptionViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        init()

        DataBinding.onSingleClick(viewDataBinding!!.textAddMortgage) {
            ActivityNavigator.startActivity(this, AddMortgageActivity::class.java)
        }

        DataBinding.onSingleClick(viewDataBinding!!.textMortgageList) {
            ActivityNavigator.startActivity(this, CustomerListActivity::class.java)
        }
        DataBinding.onSingleClick(viewDataBinding!!.textDailyExpenses) {
            ActivityNavigator.startActivity(this, ExpensesListActivity::class.java)
        }
        DataBinding.onSingleClick(viewDataBinding!!.textExchangeItem) {
            ActivityNavigator.startActivity(this, SearchExchangeActivity::class.java)
        }
        DataBinding.onSingleClick(viewDataBinding!!.textAddCustomer) {
            ActivityNavigator.startActivity(this, CustomerListActivity::class.java)
        }
        DataBinding.onSingleClick(viewDataBinding!!.textAddItems) {
            ActivityNavigator.startActivity(this, ItemsListActivity::class.java)
        }
        DataBinding.onSingleClick(viewDataBinding!!.textAddBusinessman) {
            ActivityNavigator.startActivity(this, BusinessManActivity::class.java)
        }
        DataBinding.onSingleClick(viewDataBinding!!.textBalanceSheet) {
            //ActivityNavigator.startActivity(this, ItemsListActivity::class.java)
        }
    }

    override fun init() {
        viewDataBinding!!.toolbar.stepBackButton.setOnClickListener { finish() }
        viewDataBinding!!.toolbar.toolBarHeading.text = "!! श्री !!"
    }

}