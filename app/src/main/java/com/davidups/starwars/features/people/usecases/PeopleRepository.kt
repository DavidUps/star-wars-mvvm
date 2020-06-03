package com.davidups.starwars.features.people.usecases

import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.features.people.models.view.PeopleView
import com.davidups.starwars.features.people.services.PeopleService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

interface PeopleRepository {

    fun people(): Flow<State<PeopleView>>
    fun peopleByPage(page: Int): Flow<State<PeopleView>>

    class Network(
        private val ioDispatcher: CoroutineDispatcher,
        private val service: PeopleService
    ) : PeopleRepository {

        override fun people() =
            flow {
                emit(getPeopleFromApi())
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)


        private suspend fun getPeopleFromApi() = service.getPeople()
            .run {
                if (isSuccessful && body() != null) {
                    Success(body()!!.toPeople().toPeopleView())
                } else {
                    Error(Throwable("s"))
                }
            }

        override fun peopleByPage(page: Int) =
            flow {
                emit(getPeopleByPageFromApi(page))
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)

        private suspend fun getPeopleByPageFromApi(page:Int) = service.getPeopleByPage(page)
            .run {
                if (isSuccessful && body() != null) {
                    Success(body()!!.toPeople().toPeopleView())
                } else {
                    Error(Throwable("s"))
                }
            }
    }
}

