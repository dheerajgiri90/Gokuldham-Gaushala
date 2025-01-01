package com.gokuldham.app.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import com.gokuldham.app.BR
import com.gokuldham.app.R
import com.gokuldham.app.databinding.FragmentHomeBinding
import com.gokuldham.app.ui.base.BaseFragment
import com.gokuldham.app.ui.mortgage.mortgageoption.MortgageOptionActivity
import com.gokuldham.app.util.ActivityNavigator
import com.gokuldham.app.util.DataBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), HomeNavigator {

    override val bindingVariable: Int
        get() = BR.homeViewModel

    override val layoutId: Int
        get() = R.layout.fragment_home

    override val viewModel = HomeViewModel()

    private var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (mContext == null) mContext = context.applicationContext
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        init()
    }

    override fun init() {

        DataBinding.onSingleClick(viewDataBinding!!.textMortgage) {
            ActivityNavigator.startActivity(requireActivity(), MortgageOptionActivity::class.java)
        }

    }

}