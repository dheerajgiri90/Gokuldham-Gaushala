package com.gokuldham.app.ui.exchange.exchangelist

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gokuldham.app.BR
import com.gokuldham.app.R
import com.gokuldham.app.data.ListData
import com.gokuldham.app.databinding.ActivityExchangeListBinding
import com.gokuldham.app.databinding.DialogAddExchangeBinding
import com.gokuldham.app.ui.base.BaseActivity
import com.gokuldham.app.ui.exchange.adapter.ExchangeListAdapter
import com.gokuldham.app.ui.exchange.exchangedetail.ExchangeDetailActivity
import com.gokuldham.app.ui.exchange.response.AddExchangeResponse
import com.gokuldham.app.ui.exchange.response.ExchangeListResponse
import com.gokuldham.app.ui.exchange.response.ExchangeListResponse.ExchangeData
import com.gokuldham.app.util.CommonUtils
import com.gokuldham.app.util.DataBinding
import com.gokuldham.app.util.enums.IntentKeys
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Objects

class ExchangeListActivity : BaseActivity<ActivityExchangeListBinding, ExchangeListViewModel>(),
    ExchangeListNavigator {

    override val bindingVariable: Int
        get() = BR.commonListVM

    override val layoutId: Int
        get() = R.layout.activity_exchange_list

    override val viewModel = ExchangeListViewModel()

    var mAdapter: ExchangeListAdapter? = null
    var arrayList: ArrayList<ExchangeData> = ArrayList()

    internal var delay: Long = 600 // 1 seconds after user stops typing
    internal var last_text_edit: Long = 0
    internal var handler = Handler()
    var searchKeyOffer: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        init()

        if (intent != null && intent.hasExtra(IntentKeys.BUSINESS_DATA.getKey())) {
            viewModel.businessmanData = intent.getParcelableExtra(IntentKeys.BUSINESS_DATA.getKey())
        }

        if (viewModel.businessmanData != null) {
            viewDataBinding!!.toolbar.toolBarHeading.text =
                viewModel.businessmanData?.Name.toString()
        }

        DataBinding.onSingleClick(viewDataBinding!!.textAddVillage) {
            hideKeyboard()
            showAddDialog(null)
        }

        val input_finish_checker = Runnable {
            if (System.currentTimeMillis() > last_text_edit + delay - 200) {
//                if (searchKeyOffer.length > 0 || searchKeyOffer.isEmpty()) {
                if (checkIfInternetOnDialog(tryAgainClick = {
                        viewModel.villageListAPI(searchKeyOffer)
                    })) {
                    viewModel.villageListAPI(searchKeyOffer)
                }
//                }
            }
        }

        viewDataBinding!!.searchField.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                searchKeyOffer = s.toString()
                last_text_edit = System.currentTimeMillis()
                handler.postDelayed(input_finish_checker, delay)
                if (searchKeyOffer.isEmpty()) {
                    viewDataBinding!!.imageCrossIcon.visibility = View.GONE
                } else {
                    viewDataBinding!!.imageCrossIcon.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                handler.removeCallbacks(input_finish_checker)
            }

        })
        viewDataBinding!!.imageCrossIcon.setOnClickListener {
            searchKeyOffer = ""
            viewDataBinding!!.searchField.setText("")
        }

        if (checkIfInternetOnDialog(tryAgainClick = {
                viewModel.villageListAPI(searchKeyOffer)
            })) {
            viewModel.villageListAPI(searchKeyOffer)
        }
    }

    override fun exchangeListResponse(response: ExchangeListResponse) {
        arrayList.clear()
        arrayList.addAll(response.data)
        mAdapter!!.notifyDataSetChanged()

    }

    override fun addExchangeResponse(response: AddExchangeResponse) {

        CommonUtils.showMessage(response.message, this)
        if (checkIfInternetOnDialog(tryAgainClick = {
                viewModel.villageListAPI(searchKeyOffer)
            })) {
            viewModel.villageListAPI(searchKeyOffer)
        }

    }

    override fun init() {

        viewDataBinding!!.toolbar.stepBackButton.setOnClickListener { finish() }
        viewDataBinding!!.toolbar.toolBarHeading.text = ""
        viewDataBinding!!.recyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter = ExchangeListAdapter(this, arrayList,
            onItemClick = { index ->

                val resultIntent = Intent(this, ExchangeDetailActivity::class.java)
                resultIntent.putExtra(IntentKeys.EXCHANGE_DATA.getKey(), arrayList[index])
                intentLauncher.launch(resultIntent)

            })
        viewDataBinding!!.recyclerView.setHasFixedSize(true)
        viewDataBinding!!.recyclerView.adapter = mAdapter
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        viewDataBinding!!.recyclerView.addItemDecoration(dividerItemDecoration)

    }

    private val intentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

            }
        }


    private fun showAddDialog(data: ListData?) {

        val alertDialog = Dialog(this)
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setCanceledOnTouchOutside(false)
        Objects.requireNonNull<Window>(alertDialog.window)
            .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val binding = DialogAddExchangeBinding.inflate(layoutInflater)
        alertDialog.setContentView(binding.root)
        alertDialog.setCancelable(false)
        val window = alertDialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val margin = 150
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        window?.setLayout(
            screenWidth - 2 * margin,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        window?.setGravity(Gravity.CENTER)

        binding.imageDialogClose.setOnClickListener {
            alertDialog.dismiss()
        }


        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        binding.textTodayDate.text = "$formattedDate"

        binding.textSave.setOnClickListener {

            if (checkIfInternetOnDialog(tryAgainClick = {
                    if (viewModel.checkValidation(binding)) {
                        alertDialog.dismiss()
                        viewModel.addVillageApi(binding)
                    }
                }))
                if (viewModel.checkValidation(binding)) {
                    alertDialog.dismiss()
                    viewModel.addVillageApi(binding)
                }
        }

        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }

}