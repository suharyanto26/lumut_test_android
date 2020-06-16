package com.lumut.test.util.buttomsheet

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.lumut.test.util.SettingsScreen
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lumut.test.R
import kotlinx.android.synthetic.main.bottom_no_internet.*

class NoInternetSheetDialog: BottomSheetDialogFragment() {
    interface Interface{
        fun onTryAgain()
    }

    var mInteface: Interface? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_no_internet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_try_again.setOnClickListener {
            mInteface?.onTryAgain()
        }
        btn_open_settings.setOnClickListener {
            context?.apply {
                SettingsScreen(this).showNetworkSettingScreen()
            }
        }
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        dialog.setOnShowListener {
            val handler = Handler()
            handler.postDelayed({
                val bottomDialog = dialog as BottomSheetDialog
                val bottomSheet = bottomDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
                BottomSheetBehavior.from(bottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED
            }, 250)
        }
    }
}