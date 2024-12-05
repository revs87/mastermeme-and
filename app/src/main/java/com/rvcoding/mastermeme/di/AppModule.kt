package com.rvcoding.mastermeme.di

import com.rvcoding.mastermeme.common.DispatchersProvider
import com.rvcoding.mastermeme.common.StandardDispatchersProvider
import com.rvcoding.mastermeme.ui.yourmemes.YourMemesViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    /**
     * Setup
     * */
    single<DispatchersProvider> { StandardDispatchersProvider }
    val coExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("CoroutineException: ${throwable.printStackTrace()}")
    }
    single<CoroutineExceptionHandler> { coExceptionHandler }
    single<CoroutineScope> { CoroutineScope(StandardDispatchersProvider.io + coExceptionHandler) }

    /**
     * Navigation
     * */
//    single<Navigator> {
//        DefaultNavigator(startDestination = Destination.YourAlarms)
//    }

    /**
     * Databases
     * */
//    single { AlarmsDatabase.createDb(androidContext()).dao }

    /**
     * Repositories
     * */
//    single<AlarmRepository> { AlarmRepositoryImpl(get()) }

    /**
     * ViewModels
     * */
    viewModel { YourMemesViewModel() }
}