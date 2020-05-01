package com.davidups.skell.core.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.davidups.skell.R
import com.davidups.skell.core.platform.BaseAlertDialog
import kotlinx.android.synthetic.main.info_alert_dialog.view.*

class InfoAlertDialog(context: Context) : BaseAlertDialog() {

    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.info_alert_dialog, null)
    }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    fun setTitle(text: String) {
        with(dialogView.tvAlertInfoTitle) {
            this.text = text
        }
    }

    fun setText(text: String) =
        with(dialogView.tvAlertInfoMessage) {
            this.text = text
        }

    fun setButtonText(text: String) =
        with(dialogView.btnAlertInfoAccept) {
            this.text = text
        }

    fun btnAccept(clickListener: (() -> Unit)? = null) =
        with(dialogView.btnAlertInfoAccept) {
            setClickListenerToDialogIcon(clickListener)
        }

    private fun View.setClickListenerToDialogIcon(clickListener: (() -> Unit)?) =
        setOnClickListener {
            clickListener?.invoke()
            dialog?.dismiss()
        }
}