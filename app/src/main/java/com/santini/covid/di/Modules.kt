package com.santini.covid.di

import com.santini.covid.MainViewModel
import com.santini.covid.model.responseHandler.ResponseHandler
import com.santini.covid.network.CovidInterceptor
import com.santini.covid.network.CovidInterface
import com.santini.covid.repository.Repository
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val covidModule = module {
    single(named("covid")) {
        provideRetrofit(
            get(),
            "https://corona-api.com/"

        )
    }
    single { get<Retrofit>(named("covid")).create(CovidInterface::class.java) }
    single { provideOkHttpClient(get()) }
    single { ResponseHandler() }
    single { Repository(get(), get()) }
    single { CovidInterceptor() }

}


fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


fun provideOkHttpClient(
    interceptor: CovidInterceptor): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    okHttpClientBuilder.addInterceptor(interceptor)
    return okHttpClientBuilder.build()
}