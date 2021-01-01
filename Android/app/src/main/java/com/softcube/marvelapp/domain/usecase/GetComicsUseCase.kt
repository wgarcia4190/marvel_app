package com.softcube.marvelapp.domain.usecase

import com.softcube.marvelapp.application.base.BaseUseCase
import com.softcube.marvelapp.data.remote.model.ComicRequestDTO
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.Comic
import com.softcube.marvelapp.domain.model.InfinityScrollEntity
import com.softcube.marvelapp.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow

/**
 * com.softcube.marvelapp.domain.usecase
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
typealias GetComicsBaseUseCase = BaseUseCase<ComicRequestDTO, InfinityScrollEntity<Comic>>

class GetComicsUseCase(private val repository: MarvelRepository) : GetComicsBaseUseCase {
	override suspend fun invoke(params: ComicRequestDTO): InfinityScrollEntity<Comic> =
		repository.getComics(params)
}
