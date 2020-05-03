package com.davidups.starwars.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.davidups.starwars.core.navigation.MainActivity
import kotlinx.android.synthetic.main.navigation_activity.*

abstract class BaseFragment<VB : ViewBinding>: Fragment() {

    abstract fun inflateBinding(inflater: LayoutInflater?,
                                @Nullable container: ViewGroup?,
                                @Nullable savedInstanceState: Bundle?): VB?

    protected var viewBinding: VB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = inflateBinding(inflater, container, savedInstanceState)
        return viewBinding!!.root
    }

    internal fun getBinding() = viewBinding

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) {
            if (this is MainActivity) {
                this.progress.visibility = viewStatus
            }
        }

    internal fun showSpinner(show: Boolean) {
        when (show) {
            true -> showProgress()
            false -> hideProgress()
        }
    }
}