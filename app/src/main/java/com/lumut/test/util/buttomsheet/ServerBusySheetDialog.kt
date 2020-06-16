package com.lumut.test.util.buttomsheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lumut.test.R

class ServerBusySheetDialog : BottomSheetDialogFragment() {
    var onRetry:() -> Unit? = {}
    var onDestroyCallback:() -> Unit? = {}
    var onResumeCallback:() -> Unit? = {}

    companion object {
        fun create(): ServerBusySheetDialog = ServerBusySheetDialog()
    }

    override fun onDestroy() {
        this.onDestroyCallback()
        super.onDestroy()
    }

    override fun onResume() {
        onResumeCallback()
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.server_busy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun showDialog(context: Context) {
        when {
            this.isAdded -> onStart()
            else -> {
                val fragmentManager: FragmentManager? = if (context is FragmentActivity)  (context).supportFragmentManager else if (context is Fragment) context.childFragmentManager else null
                fragmentManager?.let {
                    val fragmentTransaction: FragmentTransaction =
                        fragmentManager.beginTransaction()
                    fragmentTransaction.add(this, ServerBusySheetDialog::class.java.simpleName)
                    fragmentTransaction.commitAllowingStateLoss()
                    this.isCancelable = true
                }
            }
        }
    }
}