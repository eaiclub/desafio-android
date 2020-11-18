package com.lucasdonato.nasa.mechanism.dependencies

import com.lucasdonato.nasa.data.remote.WebServiceClient
import com.lucasdonato.nasa.data.remote.dataSource.ApodDataSource
import com.lucasdonato.nasa.data.repository.apod.ApodRepository
import com.lucasdonato.nasa.data.useCase.ApodUseCase
import com.lucasdonato.nasa.presentation.home.presenter.HomePresenter
import org.koin.dsl.module

val presenterModules = module {
    factory { HomePresenter(get()) }
}

val useCaseModules = module {
    factory { ApodUseCase(get()) }
}

val repositoryModules = module {
    factory { ApodRepository(get()) }
}

val dataSourceModules = module {
    factory { ApodDataSource(get()) }
}

val webServiceModules = module {
    single { WebServiceClient().webService }
}

val applicationModules =
    listOf(
        presenterModules, useCaseModules, repositoryModules, dataSourceModules,
        webServiceModules
    )