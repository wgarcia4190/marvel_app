package com.softcube.marvelapp.application.di

import com.softcube.marvelapp.data.repository.MarvelRepositoryImp
import com.softcube.marvelapp.domain.repository.MarvelRepository
import org.koin.dsl.module

/**
 * com.softcube.marvelapp.application.di
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
val repositoryModule = module {
	single<MarvelRepository> { MarvelRepositoryImp(apiService = get()) }
}
