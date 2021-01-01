package com.softcube.marvelapp.application

import android.app.Application
import com.softcube.marvelapp.application.di.networkModule
import com.softcube.marvelapp.application.di.repositoryModule
import com.softcube.marvelapp.application.di.useCasesModule
import com.softcube.marvelapp.application.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * com.softcube.marvelapp.application
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */

typealias UnitCallback = () -> Unit

internal class MarvelApp : Application() {

	companion object {
		lateinit var instance: MarvelApp
	}

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@MarvelApp)
			modules(
				networkModule,
				useCasesModule,
				repositoryModule,
				viewModelModule
			)
		}

		instance = this
	}
}
