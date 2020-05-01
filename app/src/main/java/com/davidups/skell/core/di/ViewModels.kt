package com.davidups.skell.core.di

import com.davidups.skell.features.people.view.viewmodels.PeopleViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { PeopleViewModel(get(), get()) }
}
