package com.softcube.marvelapp.domain.usecase

import com.softcube.marvelapp.application.base.BaseUseCase
import com.softcube.marvelapp.data.remote.model.PagingRequestDTO
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.InfinityScrollEntity
import com.softcube.marvelapp.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow

/**
 * com.softcube.marvelapp.domain.usecase
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
typealias GetCharactersBaseUseCase = BaseUseCase<PagingRequestDTO, Flow<InfinityScrollEntity<Character>>>

class GetCharactersUseCase(private val repository: MarvelRepository) : GetCharactersBaseUseCase {
	override suspend fun invoke(params: PagingRequestDTO): Flow<InfinityScrollEntity<Character>> =
		repository.getCharacters(params)
}

