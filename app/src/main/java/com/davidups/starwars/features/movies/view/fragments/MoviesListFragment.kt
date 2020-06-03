package com.davidups.starwars.features.movies.view.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.davidups.skell.R
import com.davidups.skell.databinding.FragmentMoviesBinding
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.core.extensions.showInfoAlertDialog
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.features.movies.models.view.MoviesView
import com.davidups.starwars.features.movies.view.adapters.MovieAdapter
import com.davidups.starwars.features.movies.view.viewmodels.MoviesViewModel
import com.kotlinpermissions.notNull
import org.koin.android.ext.android.inject

class MoviesListFragment : BaseFragment(R.layout.fragment_movies) {

    private val binding by viewBinding(FragmentMoviesBinding::bind)

    private val moviesViewModel: MoviesViewModel by inject()
    private val movieAdapter: MovieAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(moviesViewModel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(movies, ::handleMovies)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
    }

    private fun initView() {
        moviesViewModel.getMovies()

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = movieAdapter
        }
    }

    private fun initListeners() {}

    private fun handleMovies(movies: MoviesView?) {
        movies.notNull { movies ->
            movieAdapter.collection = movies.results.orEmpty()
        }
    }

    private fun handleShowSpinner(show: Boolean?) {
        showSpinner(show ?: false)
    }

    private fun handleFailure(failure: Throwable?) {
        showInfoAlertDialog {
            setTitle(getString(R.string.common_error))
        }.show()
    }
}