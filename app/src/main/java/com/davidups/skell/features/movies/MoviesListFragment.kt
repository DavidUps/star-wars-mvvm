package com.davidups.skell.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.davidups.skell.core.platform.BaseFragment
import com.davidups.skell.databinding.FragmentMoviesBinding

class MoviesListFragment: BaseFragment<FragmentMoviesBinding>() {

    var _binding: FragmentMoviesBinding? = null
    val binding get() = _binding!!

    override fun inflateBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMoviesBinding? {
        _binding = FragmentMoviesBinding.inflate(inflater!!, container!!, false)!!
        return binding
    }
}