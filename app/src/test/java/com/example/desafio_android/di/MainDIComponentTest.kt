//package com.example.desafio_android.di
//
//import com.example.desafio_android.data.remote.INasaApiService
//import com.google.gson.GsonBuilder
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//import okhttp3.mockwebserver.MockWebServer
//import org.koin.dsl.module
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//fun configureTestAppComponent(baseApi: String)
//        = listOf(
//    MockapiModule,
//    configureNetworkModuleForTest(baseApi),
////    useCaseModule,
//    repositoryModule
//)
//
//val MockapiModule = module {
//    factory { MockWebServer }
//}
//
//fun configureNetworkModuleForTest(baseApi: String)
//        = module{
//    single {
//        Retrofit.Builder()
//            .baseUrl(baseApi)
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .build()
//    }
//    factory{ get<Retrofit>().create(INasaApiService::class.java) }
//}
