package com.softcube.marvelapp.application.di

import com.softcube.marvelapp.presentation.details.DetailsViewModel
import com.softcube.marvelapp.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * com.softcube.marvelapp.application.di
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
val viewModelModule = module {

	viewModel {
		HomeViewModel(
			useCase = get(named("list"))
		)
	}

	viewModel {
		DetailsViewModel(
			useCase = get(named("details")),
			comicsUseCase = get(named("comics"))
		)
	}
}

