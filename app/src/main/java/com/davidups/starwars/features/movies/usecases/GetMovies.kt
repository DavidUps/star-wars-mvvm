package com.davidups.starwars.features.movies.usecases

import com.davidups.starwars.core.functional.State
import com.davidups.skell.core.interactor.UseCase
import com.davidups.starwars.features.movies.models.view.MoviesView

class GetMovies(private val moviesRepository: MoviesRepository): UseCase<State<MoviesView>, UseCase.None>() {
    override fun run(params: None?) = moviesRepository.movies()
}