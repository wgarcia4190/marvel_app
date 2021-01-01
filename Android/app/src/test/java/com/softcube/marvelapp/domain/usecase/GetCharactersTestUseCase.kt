package com.softcube.marvelapp.domain.usecase

import com.softcube.marvelapp.application.base.BaseTestUseCase
import com.softcube.marvelapp.application.base.State
import com.softcube.marvelapp.data.Data
import com.softcube.marvelapp.data.remote.model.CharacterResponseDTO
import com.softcube.marvelapp.data.remote.model.GeneralResponseDTO
import com.softcube.marvelapp.data.remote.model.PagingRequestDTO
import com.softcube.marvelapp.domain.exception.ApiError
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.InfinityScrollEntity
import kotlinx.coroutines.flow.Flow

/**
 * com.softcube.marvelapp.domain.usecase
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class GetCharactersTestUseCase(
	private val state: State
) : BaseTestUseCase<PagingRequestDTO, InfinityScrollEntity<Character>>(state),
	GetCharactersBaseUseCase {

	override fun getValue(params: PagingRequestDTO): InfinityScrollEntity<Character> {
		return when (state) {
			State.SUCCESS -> {

				val characterListResponse: GeneralResponseDTO<CharacterResponseDTO, Character> = Data.getCharacters()

				val characters = characterListResponse.data.results.map {
					it.toDomain()
				}

				val result = InfinityScrollEntity<Character>(
					total = characterListResponse.data.total,
					data = characters
				)

				return result
			}
			else -> throw ApiError(message = "Something went wrong")
		}
	}

	override suspend fun invoke(params: PagingRequestDTO): Flow<InfinityScrollEntity<Character>> =
		execute(params)

}
