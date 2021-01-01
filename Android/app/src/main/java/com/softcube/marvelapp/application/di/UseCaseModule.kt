package com.softcube.marvelapp.application.di

import com.softcube.marvelapp.domain.repository.MarvelRepository
import com.softcube.marvelapp.domain.usecase.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * com.softcube.marvelapp.application.di
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
val useCasesModule = module {
	single(named("list")) { provideCharacterListUseCase(get()) }
	single(named("details")) { provideCharacterUseCase(get()) }
	single(named("comics")) { provideComicListUseCase(get()) }
}

fun provideCharacterListUseCase(marvelRepository: MarvelRepository): GetCharactersBaseUseCase {
	return GetCharactersUseCase(marvelRepository)
}

fun provideCharacterUseCase(marvelRepository: MarvelRepository): GetCharacterBaseUseCase {
	return GetCharacterUseCase(marvelRepository)
}

fun provideComicListUseCase(marvelRepository: MarvelRepository): GetComicsBaseUseCase {
	return GetComicsUseCase(marvelRepository)
}

