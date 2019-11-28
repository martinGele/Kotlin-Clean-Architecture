package com.martin.kotlinclean.di

import com.martin.kotlinclean.model.AnimalApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: AnimalApiService)
}