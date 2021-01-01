package com.softcube.marvelapp.domain.repository

import com.softcube.marvelapp.data.remote.model.ComicRequestDTO
import com.softcube.marvelapp.data.remote.model.PagingRequestDTO
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.Comic
import com.softcube.marvelapp.domain.model.InfinityScrollEntity
import kotlinx.coroutines.flow.Flow

/**
 * com.softcube.marvelapp.domain.repository
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
interface MarvelRepository {
	suspend fun getCharacters(request: PagingRequestDTO): Flow<InfinityScrollEntity<Character>>
	suspend fun getCharacter(characterId: Long): Character
	suspend fun getComics(request: ComicRequestDTO): InfinityScrollEntity<Comic>
}
