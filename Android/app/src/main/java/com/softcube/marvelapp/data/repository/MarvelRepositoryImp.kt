package com.softcube.marvelapp.data.repository

import com.softcube.marvelapp.application.AppConfiguration
import com.softcube.marvelapp.data.remote.api.MarvelService
import com.softcube.marvelapp.data.remote.model.ComicRequestDTO
import com.softcube.marvelapp.data.remote.model.PagingRequestDTO
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.Comic
import com.softcube.marvelapp.domain.model.InfinityScrollEntity
import com.softcube.marvelapp.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * com.softcube.marvelapp.data.repository
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
class MarvelRepositoryImp(private val apiService: MarvelService) : MarvelRepository {
	override suspend fun getCharacters(
		request: PagingRequestDTO
	): Flow<InfinityScrollEntity<Character>> = flow {
		val ts = AppConfiguration.ts

		val characterListResponse = apiService.getCharacters(
			apikey = AppConfiguration.publicKey,
			ts = ts,
			hash = AppConfiguration.hash(ts),
			limit = request.limit,
			offset = request.offset
		)

		val characters = characterListResponse.data.results.map {
			it.toDomain()
		}

		val result = InfinityScrollEntity<Character>(
			total = characterListResponse.data.total,
			data = characters
		)

		emit(result)
	}

	override suspend fun getCharacter(characterId: Long): Character {
		val ts = AppConfiguration.ts

		val characterResponse = apiService.getCharacter(
			apikey = AppConfiguration.publicKey,
			ts = ts,
			hash = AppConfiguration.hash(ts),
			id = characterId
		)

		return characterResponse.data.results.last().toDomain()
	}

	override suspend fun getComics(request: ComicRequestDTO): InfinityScrollEntity<Comic> {
		val ts = AppConfiguration.ts

		val comicListResponse = apiService.getComics(
			apikey = AppConfiguration.publicKey,
			ts = ts,
			hash = AppConfiguration.hash(ts),
			limit = request.limit,
			offset = request.offset,
			id = request.characterId
		)

		val comics = comicListResponse.data.results.map {
			it.toDomain()
		}

		val result = InfinityScrollEntity<Comic>(
			total = comicListResponse.data.total,
			data = comics
		)

		return result
	}
}
