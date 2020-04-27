package com.davidups.skell.core.platform

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidups.skell.core.extensions.Constants.Companion.VW_TAG

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Throwable> = MutableLiveData()
    var showSpinner: MutableLiveData<Boolean> = MutableLiveData()

    protected fun handleFailure(failure: Throwable?) {
        this.failure.value = failure
    }

    protected fun handleShowSpinner(show: Boolean) {
        this.showSpinner.value = show
    }
}