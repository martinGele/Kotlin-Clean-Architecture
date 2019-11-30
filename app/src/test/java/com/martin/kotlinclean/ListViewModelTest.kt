package com.martin.kotlinclean

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.martin.kotlinclean.di.AppModule
import com.martin.kotlinclean.di.DaggerViewModelComponent
import com.martin.kotlinclean.model.Animal
import com.martin.kotlinclean.model.AnimalApiService
import com.martin.kotlinclean.model.ApiKey
import com.martin.kotlinclean.util.SharedPreferencesHelper
import com.martin.kotlinclean.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class ListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val key = "Test key"

    @Mock
    lateinit var animalApiService: AnimalApiService

    @Mock
    lateinit var prefs: SharedPreferencesHelper

    val application = Mockito.mock(Application::class.java)

    var listViewModel = ListViewModel(application, true)


    @Test
    fun getAnimalsSuccess() {
        Mockito.`when`(prefs.getApiKey()).thenReturn(key)
        val animal = Animal("cow", null, null, null, null, null, null)
        val animalList = listOf(animal)

        val testSingle = Single.just(animalList)

        Mockito.`when`(animalApiService.getAnimals(key)).thenReturn(testSingle)


        listViewModel.refresh()

        Assert.assertEquals(1, listViewModel.animals.value?.size)
        Assert.assertEquals(false, listViewModel.loadError.value)

        Assert.assertEquals(false, listViewModel.loading.value)
    }


    @Test
    fun getAnimalsFailiure() {

        Mockito.`when`(prefs.getApiKey()).thenReturn(key)
        val testSingle = Single.error<List<Animal>>(Throwable())
        val keySingle = Single.just(ApiKey("OK", key))

        Mockito.`when`(animalApiService.getAnimals(key)).thenReturn(testSingle)
        Mockito.`when`(animalApiService.getApiKey()).thenReturn(keySingle)

        listViewModel.refresh()

        Assert.assertEquals(null, listViewModel.animals.value?.size)
        Assert.assertEquals(true, listViewModel.loadError.value)

        Assert.assertEquals(false, listViewModel.loading.value)

    }

    @Test
    fun getKeySuccess() {
        Mockito.`when`(prefs.getApiKey()).thenReturn(null)
        val apiKey = ApiKey("ok", key)
        val keySingle = Single.just(apiKey)
        Mockito.`when`(animalApiService.getApiKey()).thenReturn(keySingle)

        val animal = Animal("cow", null, null, null, null, null, null)

        val animalList = listOf(animal)
        val testSingle = Single.just(animalList)
        Mockito.`when`(animalApiService.getAnimals(key)).thenReturn(testSingle)

        listViewModel.refresh()



        Assert.assertEquals(1, listViewModel.animals.value?.size)
        Assert.assertEquals(false, listViewModel.loadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)

    }


    @Test
    fun getKeyFailiure(){

        Mockito.`when`(prefs.getApiKey()).thenReturn(null)
        val keySingle= Single.error<ApiKey>(Throwable())
        Mockito.`when`(animalApiService.getApiKey()).thenReturn(keySingle)

        listViewModel.refresh()

        Assert.assertEquals(null, listViewModel.animals.value?.size)
        Assert.assertEquals(true, listViewModel.loadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        DaggerViewModelComponent.builder()
            .appModule(AppModule(application))
            .apiModule(ApiModuleTest(animalApiService))
            .prefsModule(PrefsModuleTest(prefs))
            .build()
            .inject(listViewModel)
    }


    @Before
    fun setupRxSchedulers() {
        val imidiate = object : Scheduler() {
            override fun createWorker(): Worker {

                return ExecutorScheduler.ExecutorWorker(Executor {
                    it.run()
                }, true)
            }
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { shceduler ->

            imidiate
        }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler ->
            imidiate
        }

    }
}