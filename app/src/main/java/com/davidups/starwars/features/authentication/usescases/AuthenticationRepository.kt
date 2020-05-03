package com.davidups.starwars.features.authentication.usescases

import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.Result
import com.davidups.starwars.features.authentication.models.entity.UserEntity
import com.davidups.starwars.features.authentication.services.AuthenticationService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface AuthenticationRepository {

    fun signIn(user: UserEntity): Flow<Result<Boolean>>
    fun signUp(user: UserEntity): Flow<Result<Boolean>>

    class Network(
        private val ioDispatcher: CoroutineDispatcher,
        private val service: AuthenticationService
    ) : AuthenticationRepository {

        override fun signIn(user: UserEntity): Flow<Result<Boolean>> =
            flow {
                emit(service.signIn(user))
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)


        override fun signUp(user: UserEntity): Flow<Result<Boolean>> =
            flow {
                emit(service.signUp())
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)
    }

}