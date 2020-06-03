package com.davidups.starwars.features.authentication.view.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.davidups.skell.R
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.skell.databinding.FragmentAuthenticationBinding
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.features.authentication.view.adapters.AuthenticationViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_authentication.*

class AuthenticationFragment: BaseFragment(R.layout.fragment_authentication) {

    private val binding by viewBinding(FragmentAuthenticationBinding::bind)

    private lateinit var container: ViewGroup

    private lateinit var authenticationAdapter: AuthenticationViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authenticationAdapter = AuthenticationViewPagerAdapter(childFragmentManager,lifecycle)

        initView()
    }

    private fun initView() {

        if (FirebaseAuth.getInstance().currentUser != null) {
            view?.findNavController()?.navigate(R.id.action_authentication_to_movies)
        }else {
            binding.vpAuthentication.apply {
                adapter = authenticationAdapter
                isUserInputEnabled = false
            }

            authenticationAdapter.collection =
                mutableListOf(SignInFragment(), SignUpFragment())

            initListners()
        }
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