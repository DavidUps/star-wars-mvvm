package com.davidups.starwars.features.people.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.skell.core.extensions.cancelIfActive
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.core.functional.Error
import com.davidups.skell.core.interactor.UseCase
import com.davidups.skell.core.platform.BaseViewModel
import com.davidups.skell.features.people.models.entity.PeopleEntity
import com.davidups.skell.features.people.models.view.PeopleView
import com.davidups.skell.features.people.usecases.GetPeople
import com.davidups.skell.features.people.usecases.GetPeopleByPage
import com.kotlinpermissions.notNull
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job

class PeopleViewModel(
    private val getPeople: GetPeople,
    private val getPeopleByPage: GetPeopleByPage
) : BaseViewModel() {

    var people = MutableLiveData<PeopleView>()
    var getPeopleJob: Job? = null

    fun getPeople() {
        getPeopleJob.cancelIfActive()
        getPeopleJob = viewModelScope.launch {
            getPeople(UseCase.None())
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<PeopleView> -> {
                            people.value = result.data
                        }
                        is Error -> {
                        }
                    }
                }
        }
    }

    fun loadMorePeople(people: PeopleEntity?) {
        people.notNull { people ->
            people.next.notNull { nextPage ->
                val pageSplit = nextPage.split("page=")
                getPeopleByPage(pageSplit[1].toInt())
            }
        }
    }

    private fun getPeopleByPage(page: Int) {
        getPeopleJob.cancelIfActive()
        getPeopleJob = viewModelScope.launch {
            getPeopleByPage(GetPeopleByPage.Params(page))
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<PeopleView> -> {
                            people.value = result.data
                        }
                        is Error -> {
                        }
                    }
                }
        }
    }
}