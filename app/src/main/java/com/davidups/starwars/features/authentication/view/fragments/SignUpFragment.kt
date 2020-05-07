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
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.features.authentication.models.view.UserView
import com.davidups.starwars.features.authentication.view.viewmodels.AuthenticationViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpFragment() : BaseFragment(R.layout.fragment_sign_up) {

    private val authenticationViewModel: AuthenticationViewModel by viewModel()

    val binding by viewBinding(FragmentSignUpBinding::bind)


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

        initListeners()
    }

    private fun initListeners() {

        binding.btnSignIn.setOnClickListener {
            val user = UserView(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            authenticationViewModel.signUp(user)
        }
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