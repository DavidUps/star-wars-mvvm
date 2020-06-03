package com.davidups.starwars.features.authentication.view.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.davidups.skell.R
import com.davidups.starwars.core.extensions.showInfoAlertDialog
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.skell.databinding.FragmentSignInBinding
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.features.authentication.models.view.UserView
import com.davidups.starwars.features.authentication.view.viewmodels.AuthenticationViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SignInFragment() : BaseFragment(R.layout.fragment_sign_in) {

    private val authenticationViewModel: AuthenticationViewModel by viewModel()

    val binding by viewBinding(FragmentSignInBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(authenticationViewModel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(auth, ::handleAuth)
            observe(putUserResponse, ::handlePutUser)
            observe(getUserResponse, ::handleGetUser)
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
            authenticationViewModel.signIn(user)
        }
    }

    private fun handleAuth(b: Boolean?) {
        val user = UserView(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        authenticationViewModel.putUser(user)
    }

    private fun handlePutUser(b: Boolean?) {
        authenticationViewModel.getUser()
    }

    private fun handleGetUser(user: UserView?) {
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