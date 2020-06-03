package com.davidups.starwars.features.movies.usecases

import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.features.movies.models.view.MoviesView
import com.davidups.starwars.features.movies.services.MoviesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

interface MoviesRepository {

    fun movies(): Flow<State<MoviesView>>

    class Network(
        private val ioDispatcher: CoroutineDispatcher,
        private val service: MoviesService
    ) : MoviesRepository {

        override fun movies() =
            flow {
                emit(getMoviesFromApi())
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)


        private suspend fun getMoviesFromApi() = service.getMovies()
            .run {
                if (isSuccessful && body() != null) {
                    Success(body()!!.toMovies().toMoviesView())
                } else {
                    Error(Throwable("s"))
                }
            }

    }
}

