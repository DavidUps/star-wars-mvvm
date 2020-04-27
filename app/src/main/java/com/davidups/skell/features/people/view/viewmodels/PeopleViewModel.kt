package com.davidups.skell.features.people.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.skell.core.extensions.cancelIfActive
import com.davidups.skell.core.functional.Success
import com.davidups.skell.core.functional.Error
import com.davidups.skell.core.interactor.UseCase
import com.davidups.skell.core.platform.BaseViewModel
import com.davidups.skell.features.people.models.view.PeopleView
import com.davidups.skell.features.people.usecases.GetPeople
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job

class PeopleViewModel(private val getPeople: GetPeople) : BaseViewModel() {

    var people = MutableLiveData<PeopleView>()
    var getPeopleJob: Job? = null

    fun getPeople() {
        getPeopleJob.cancelIfActive()
        getPeopleJob = viewModelScope.launch {
            getPeople(UseCase.None())
                .onStart {
                    handleShowSpinner(true)
                }
                .onEach {
                    handleShowSpinner(false)
                }
                .catch { failure ->
                    handleFailure(failure)
                }
                .collect {
                    when (it){
                        is Success<PeopleView> -> {
                            people.value = it.data
                        }
                        is Error -> {
                        }
                    }
                }
        }
    }
}