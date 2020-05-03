package com.davidups.starwars.features.authentication.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlin.properties.Delegates

class AuthenticationViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    internal var collection: List<Fragment> by Delegates.observable(emptyList()) { _, _, _ ->
        for (n in collection.indices) {
            createFragment(n)
        }
    }

    override fun createFragment(position: Int): Fragment {
        return collection[position]
    }

    override fun getItemCount(): Int {
        return collection.size
    }
}