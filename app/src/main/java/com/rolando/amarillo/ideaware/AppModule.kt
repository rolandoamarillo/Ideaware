package com.rolando.amarillo.ideaware

import com.rolando.amarillo.ideaware.api.FixturesApi
import com.rolando.amarillo.ideaware.api.ResultsApi
import com.rolando.amarillo.ideaware.repositories.FixturesRepository
import com.rolando.amarillo.ideaware.repositories.ResultsRepository
import com.rolando.amarillo.ideaware.viewmodels.FixturesViewModel
import com.rolando.amarillo.ideaware.viewmodels.ResultsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<Retrofit> {
        Retrofit
            .Builder()
            .baseUrl(androidContext().getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<FixturesApi> {
        get<Retrofit>().create(FixturesApi::class.java)
    }

    single<ResultsApi> {
        get<Retrofit>().create(ResultsApi::class.java)
    }

    single {
        FixturesRepository(get())
    }

    single {
        ResultsRepository(get())
    }

    viewModel { FixturesViewModel(get()) }

    viewModel { ResultsViewModel(get()) }
}