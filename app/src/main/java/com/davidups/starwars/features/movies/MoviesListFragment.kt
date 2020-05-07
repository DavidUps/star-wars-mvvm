package com.davidups.starwars.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.davidups.skell.R
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.skell.databinding.FragmentMoviesBinding
import com.davidups.starwars.core.platform.viewBinding.viewBinding

class MoviesListFragment: BaseFragment(R.layout.fragment_movies) {

    val binding by viewBinding(FragmentMoviesBinding::bind)
}