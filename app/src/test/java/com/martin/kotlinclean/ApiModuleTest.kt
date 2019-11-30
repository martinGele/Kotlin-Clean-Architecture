package com.martin.kotlinclean

import com.martin.kotlinclean.di.ApiModule
import com.martin.kotlinclean.model.AnimalApiService

class ApiModuleTest(val mockService: AnimalApiService):ApiModule() {

    override fun provideAnimalApiService(): AnimalApiService {
        return mockService
    }
}