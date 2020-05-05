package com.davidups.starwars.features.authentication.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.davidups.skell.R
import com.davidups.skell.core.extensions.onClick
import com.davidups.skell.core.extensions.showInfoAlertDialog
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.skell.databinding.FragmentSignInBinding
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.features.authentication.models.view.UserView
import com.davidups.starwars.features.authentication.view.viewmodels.AuthenticationViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SignInFragment(container: ViewGroup) : BaseFragment<FragmentSignInBinding>() {

    private val authenticationViewModel: AuthenticationViewModel by viewModel()

    var _binding: FragmentSignInBinding? = null
    val binding get() = _binding!!

    val container = container

    override fun inflateBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSignInBinding? {
        _binding = FragmentSignInBinding.inflate(inflater!!, this.container, false)
        return _binding
    }

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