package com.davidups.skell.core.extensions

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.davidups.skell.core.utils.InfoAlertDialog

inline fun Fragment.showInfoAlertDialog(func: InfoAlertDialog.() -> Unit): AlertDialog =
    InfoAlertDialog(this.context!!).apply {
        func()
    }.create()