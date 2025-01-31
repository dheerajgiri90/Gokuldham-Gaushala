package com.gokuldham.app.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.gokuldham.app.BR
import com.gokuldham.app.R
import com.gokuldham.app.data.local.AppPreference
import com.gokuldham.app.data.local.PreferenceKeys
import com.gokuldham.app.databinding.ActivitySplashBinding
import com.gokuldham.app.ui.base.BaseActivity
import com.gokuldham.app.ui.login.LoginActivity
import com.gokuldham.app.ui.navigation.NavigationActivity
import com.gokuldham.app.util.CommonUtils

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    override val bindingVariable: Int
        get() = BR.splashVM
    override val layoutId: Int
        get() = R.layout.activity_splash

    override val viewModel = SplashViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        init()
    }

    override fun init() {
        viewModel.navigator = this
        moveToNextScreen()
    }

    override fun moveToNextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({

            if (CommonUtils.isStringNullOrBlank(AppPreference.getValue(PreferenceKeys.ACCESS_TOKEN))) {
                val mainIntent = Intent(this, LoginActivity::class.java)
                startActivity(mainIntent)
                finish()
            } else {
                val mainIntent = Intent(this, NavigationActivity::class.java)
                startActivity(mainIntent)
                finish()
            }

        }, 2000)

    }

}