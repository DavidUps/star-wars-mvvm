package com.davidups.starwars.features.authentication.usescases

import com.davidups.skell.core.extensions.empty
import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.features.authentication.models.entity.UserEntity
import com.davidups.starwars.features.authentication.models.view.UserView
import com.davidups.starwars.features.authentication.services.AuthenticationService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

interface AuthenticationRepository {

    fun signIn(user: UserEntity): Flow<State<Boolean>>
    fun signUp(user: UserEntity): Flow<State<Boolean>>
    fun putUser(user: UserEntity): Flow<State<Boolean>>
    fun getUser(): Flow<State<UserView>>

    class Network(
        private val ioDispatcher: CoroutineDispatcher,
        private val service: AuthenticationService
    ) : AuthenticationRepository {

        override fun signIn(user: UserEntity): Flow<State<Boolean>> =
            flow {
                emit(service.signIn(user))
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)

        override fun signUp(user: UserEntity): Flow<State<Boolean>> =
            flow {
                emit(service.signUp(user))
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)

        override fun putUser(user: UserEntity): Flow<State<Boolean>> =
            flow {
                emit(service.putUser(user))
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)

        override fun getUser(): Flow<State<UserView>> = callbackFlow {
            val subscription = service.getUserReference().addSnapshotListener { snapshot, _ ->
                if(snapshot!!.exists()){
                    offer(Success(UserView(snapshot.getString("email")?:String.empty(),
                        snapshot.getString("password")?:String.empty())))
                } else {
                    offer(Error(Throwable("s")))
                }
            }
            awaitClose { subscription.remove() }
        }
    }
}