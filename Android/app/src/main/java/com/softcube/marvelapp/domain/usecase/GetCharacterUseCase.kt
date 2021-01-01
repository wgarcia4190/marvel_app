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
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
typealias GetCharacterBaseUseCase = BaseUseCase<Long, Character>

class GetCharacterUseCase(private val repository: MarvelRepository) : GetCharacterBaseUseCase {
	override suspend fun invoke(params: Long): Character =
		repository.getCharacter(params)
}
