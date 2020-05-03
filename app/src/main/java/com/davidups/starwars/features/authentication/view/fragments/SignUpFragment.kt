package com.davidups.starwars.features.authentication.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.davidups.skell.R
import com.davidups.skell.core.extensions.showInfoAlertDialog
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.skell.databinding.FragmentSignUpBinding
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.features.authentication.view.viewmodels.AuthenticationViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpFragment(container: ViewGroup) : BaseFragment<FragmentSignUpBinding>() {

    private val authenticationViewModel: AuthenticationViewModel by viewModel()

    var _binding: FragmentSignUpBinding? = null
    val binding get() = _binding!!

    val container = container

    override fun inflateBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSignUpBinding? {
        _binding = FragmentSignUpBinding.inflate(inflater!!, this.container,false)
        return binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(authenticationViewModel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(auth, ::handleAuth)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun handleAuth(b: Boolean?) {
        view?.findNavController()?.navigate(R.id.action_authentication_to_movies)
    }

    private fun handleShowSpinner(show: Boolean?) {
        showSpinner(show ?: false)
    }

    private fun handleFailure(failure: Throwable?) {
        showInfoAlertDialog {
            setTitle(getString(R.string.common_error))
        }
    }
}