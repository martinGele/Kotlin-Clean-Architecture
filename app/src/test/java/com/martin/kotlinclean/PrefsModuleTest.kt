package com.martin.kotlinclean

import android.app.Application
import android.content.SharedPreferences
import com.martin.kotlinclean.di.PrefsModule
import com.martin.kotlinclean.util.SharedPreferencesHelper

class PrefsModuleTest(val mockPrefs:SharedPreferencesHelper):PrefsModule() {

    override fun providesSharedPreferences(app: Application): SharedPreferencesHelper {
        return mockPrefs
    }
}