package com.softcube.marvelapp.data

import com.softcube.marvelapp.data.remote.model.CharacterResponseDTO
import com.softcube.marvelapp.data.remote.model.Collection
import com.softcube.marvelapp.data.remote.model.Data
import com.softcube.marvelapp.data.remote.model.GeneralResponseDTO
import com.softcube.marvelapp.data.remote.model.Thumbnail
import com.softcube.marvelapp.domain.model.Character

/**
 * com.softcube.marvelapp.data
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
object Data {

	fun getCharacters(): GeneralResponseDTO<CharacterResponseDTO, Character> {
		val result: GeneralResponseDTO<CharacterResponseDTO, Character> = GeneralResponseDTO(
			code = 200,
			status = "Ok",
			copyright = "© 2021 MARVEL",
			attributionText = "Data provided by Marvel. © 2021 MARVEL",
			attributionHTML = "<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>",
			etag = "2ee13295919329770cc34300fc1b8359587b8308",
			data = Data<CharacterResponseDTO, Character>(
				offset = 0,
				limit = 2,
				total = 2,
				count = 2,
				results = mutableListOf(
					CharacterResponseDTO(
						id = 1011334,
						name = "3-D Man",
						description = "",
						modified = "2014-04-29T14:18:17-0400",
						thumbnail = Thumbnail(
							path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
							extension = "jpg"
						),
						resourceURI = "http://gateway.marvel.com/v1/public/characters/1011334",
						comics = Collection(
							0, "", 0, mutableListOf()
						),
						stories = Collection(
							0, "", 0, mutableListOf()
						),
						events = Collection(
							0, "", 0, mutableListOf()
						),
						urls = mutableListOf()
					),
					CharacterResponseDTO(
						id = 1017100,
						name = "A-Bomb (HAS)",
						description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!",
						modified = "2013-09-18T15:54:04-0400",
						thumbnail = Thumbnail(
							path = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16",
							extension = "jpg"
						),
						resourceURI = "http://gateway.marvel.com/v1/public/characters/1017100",
						comics = Collection(
							0, "", 0, mutableListOf()
						),
						stories = Collection(
							0, "", 0, mutableListOf()
						),
						events = Collection(
							0, "", 0, mutableListOf()
						),
						urls = mutableListOf()
					)
				)
			)
		)

		return result
	}
}

/*

data class Data<T, S>(
	@SerializedName("offset") val offset: Int,
	@SerializedName("limit") val limit: Int,
	@SerializedName("total") val total: Int,
	@SerializedName("count") val count: Int,
	@SerializedName("results") val results: List<T>,
) where T: Entity<S>*/
