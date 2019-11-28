package com.martin.kotlinclean.di

import android.app.Activity
import android.app.Application
import com.martin.kotlinclean.util.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
class PrefsModule {


    @Provides
    @Singleton
    @TypeOfContext(CONTEXT_APP)
    fun providesSharedPreferences(app: Application): SharedPreferencesHelper {
        return SharedPreferencesHelper(app)
    }


    @Provides
    @Singleton
    @TypeOfContext(CONTEXT_ACTIVITY)
    fun providesActivitySharedPreferences(app: Activity): SharedPreferencesHelper {
        return SharedPreferencesHelper(app)
    }
}

const val CONTEXT_APP = "Application context"
const val CONTEXT_ACTIVITY = "Activity context"

@Qualifier
annotation class TypeOfContext(val type: String)