package com.lucasdonato.nasa.mechanism.dependencies

import org.koin.dsl.module


val presenterModules = module {

}

val useCaseModules = module {

}

val repositoryModules = module {
}

val dataSourceModules = module {
}

val mechanismModules = module {
}

val applicationModules =
    listOf(
        presenterModules, useCaseModules, repositoryModules, dataSourceModules,
        mechanismModules
    )