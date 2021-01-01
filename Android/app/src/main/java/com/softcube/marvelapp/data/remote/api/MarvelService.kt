package com.softcube.marvelapp.data.remote.api

import com.softcube.marvelapp.data.remote.model.CharacterResponseDTO
import com.softcube.marvelapp.data.remote.model.ComicResponseDTO
import com.softcube.marvelapp.data.remote.model.GeneralResponseDTO
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.Comic
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * com.softcube.marvelapp.data.remote.api
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
interface MarvelService {
	@GET("v1/public/characters")
	suspend fun getCharacters(
		@Query("apikey") apikey: String,
		@Query("ts") ts: String,
		@Query("hash") hash: String,
		@Query("limit") limit: Int,
		@Query("offset") offset: Int
	): GeneralResponseDTO<CharacterResponseDTO, Character>

	@GET("v1/public/characters/{id}")
	suspend fun getCharacter(
		@Path("id") id: Long,
		@Query("apikey") apikey: String,
		@Query("ts") ts: String,
		@Query("hash") hash: String
	): GeneralResponseDTO<CharacterResponseDTO, Character>

	@GET("v1/public/characters/{id}/comics")
	suspend fun getComics(
		@Path("id") id: Long,
		@Query("apikey") apikey: String,
		@Query("ts") ts: String,
		@Query("hash") hash: String,
		@Query("limit") limit: Int,
		@Query("offset") offset: Int
	): GeneralResponseDTO<ComicResponseDTO, Comic>
}
