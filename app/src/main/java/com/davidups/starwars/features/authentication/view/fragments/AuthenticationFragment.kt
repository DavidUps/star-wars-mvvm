package com.davidups.starwars.features.authentication.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.davidups.skell.R
import com.davidups.skell.core.extensions.showInfoAlertDialog
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.skell.databinding.FragmentAuthenticationBinding
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.features.authentication.view.adapters.AuthenticationViewPagerAdapter
import com.davidups.starwars.features.authentication.view.viewmodels.AuthenticationViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_authentication.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class AuthenticationFragment: BaseFragment<FragmentAuthenticationBinding>() {

    var _binding: FragmentAuthenticationBinding? = null
    val binding get() = _binding!!

    private lateinit var container: ViewGroup

    private lateinit var authenticationAdapter: AuthenticationViewPagerAdapter

    override fun inflateBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAuthenticationBinding? {
        this.container = container!!
        _binding = FragmentAuthenticationBinding.inflate(inflater!!, container!!, false)
        return _binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authenticationAdapter = AuthenticationViewPagerAdapter(childFragmentManager,lifecycle)

        initView()
        initListners()
    }

    private fun initView() {

        binding.vpAuthentication.apply {
            adapter = authenticationAdapter
            isUserInputEnabled = false
        }

        authenticationAdapter.collection =
            mutableListOf(SignInFragment(container), SignUpFragment(container))
    }

    private fun initListners() {
        TabLayoutMediator(
            tabAuthentication,
            vpAuthentication,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = when (position) {
                    0 -> "Sign In"
                    1 -> "Sign Up"
                    else -> ""
                }
            }).attach()
    }


}