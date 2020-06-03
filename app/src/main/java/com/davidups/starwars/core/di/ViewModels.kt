package com.davidups.starwars.core.di

import com.davidups.starwars.features.authentication.view.viewmodels.AuthenticationViewModel
import com.davidups.starwars.features.movies.view.viewmodels.MoviesViewModel
import com.davidups.starwars.features.people.view.viewmodels.PeopleViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {


    viewModel { AuthenticationViewModel(get(), get(), get(), get()) }

    viewModel { MoviesViewModel(get()) }

    viewModel { PeopleViewModel(get(), get()) }

}
