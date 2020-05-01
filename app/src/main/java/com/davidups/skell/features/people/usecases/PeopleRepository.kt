package com.davidups.skell.features.people.usecases

import com.davidups.skell.core.functional.Error
import com.davidups.skell.core.functional.Result
import com.davidups.skell.core.functional.Success
import com.davidups.skell.features.people.models.view.PeopleView
import com.davidups.skell.features.people.services.PeopleService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*


interface PeopleRepository {

    fun people(): Flow<Result<PeopleView>>
    fun peopleByPage(page: Int): Flow<Result<PeopleView>>

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
                emit(getPeopleByPageFromApi())
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)

        private suspend fun getPeopleByPageFromApi() = service.getPeople()
            .run {
                if (isSuccessful && body() != null) {
                    Success(body()!!.toPeople().toPeopleView())
                } else {
                    Error(Throwable("s"))
                }
            }
    }
}

