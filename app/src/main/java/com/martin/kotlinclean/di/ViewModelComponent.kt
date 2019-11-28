package com.martin.kotlinclean.di

import com.martin.kotlinclean.viewmodel.ListViewModel
import dagger.Component
import dagger.Module
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModule::class,PrefsModule::class,AppModule::class])
interface ViewModelComponent {

    fun inject(viewModel:ListViewModel)
}