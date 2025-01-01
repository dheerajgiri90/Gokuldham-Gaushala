package com.gokuldham.app.ui.account

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.core.app.NotificationManagerCompat
import com.gokuldham.app.BR
import com.gokuldham.app.R
import com.gokuldham.app.databinding.FragmentAccountBinding
import com.gokuldham.app.ui.base.BaseFragment
import com.gokuldham.app.util.ClearPreference
import com.gokuldham.app.util.DataBinding
import com.gokuldham.app.util.DialogConstant

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>(), AccountNavigator {

    override val bindingVariable: Int
        get() = BR.accountViewModel

    override val layoutId: Int
        get() = R.layout.fragment_account

    override val viewModel = AccountViewModel()

    private var mContext: Context? = null

    override fun onAttach(@NonNull context: Context) {
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

        DataBinding.onSingleClick(viewDataBinding!!.textViewLogout) {
            DialogConstant.showLogoutAlertDialog(resources.getString(R.string.logout),
                resources.getString(R.string.want_to_logout),
                requireActivity(),
                object : DialogConstant.OnOkCancelListener {
                    override fun onClickOk() {

                        NotificationManagerCompat.from(requireContext()).cancelAll()
                        ClearPreference.clearDataLogout(mContext!!)
//                        if (checkIfInternetOnDialog(tryAgainClick = {
//                                viewModel.logoutApi()
//                            }))
//                            viewModel.logoutApi()
                    }

                    override fun onClickCancel() {
                    }
                })
        }

    }

    override fun navigateToSignIn(message: String) {
        NotificationManagerCompat.from(requireContext()).cancelAll()
        ClearPreference.clearDataLogout(mContext!!)
    }

}