package com.martin.kotlinclean.di

import com.martin.kotlinclean.model.Animal
import com.martin.kotlinclean.model.AnimalApi
import com.martin.kotlinclean.model.AnimalApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
open class ApiModule {
    private val URL: String = "https://us-central1-apis-4674e.cloudfunctions.net/"


    @Provides
    fun provideAnimalAPi(): AnimalApi {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AnimalApi::class.java)
    }


    @Provides
   open fun provideAnimalApiService():AnimalApiService{

        return AnimalApiService()
    }


}