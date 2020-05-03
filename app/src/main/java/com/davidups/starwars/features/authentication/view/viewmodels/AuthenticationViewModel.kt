package com.davidups.starwars.features.authentication.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.skell.core.extensions.cancelIfActive
import com.davidups.skell.core.interactor.UseCase
import com.davidups.skell.core.platform.BaseViewModel
import com.davidups.skell.features.people.models.view.PeopleView
import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.features.authentication.models.view.UserView
import com.davidups.starwars.features.authentication.usescases.SignIn
import com.davidups.starwars.features.authentication.usescases.SignUp
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val signIn: SignIn,
    private val signUp: SignUp
) : BaseViewModel() {

    var auth = MutableLiveData<Boolean>()
    var getAuthJob: Job? = null

    fun signIn(user: UserView) {
        getAuthJob.cancelIfActive()
        getAuthJob = viewModelScope.launch {
            signIn(SignIn.Params(user.toUser().toUserEntity()))
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<Boolean> -> {
                            auth.value = result.data
                        }
                        is Error -> {
                        }
                    }
                }
        }
    }

    fun signUp(user: UserView) {
        getAuthJob.cancelIfActive()
        getAuthJob = viewModelScope.launch {
            signUp(SignUp.Params(user.toUser().toUserEntity()))
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<Boolean> -> {
                            auth.value = result.data
                        }
                        is Error -> {
                        }
                    }
                }
        }
    }
}