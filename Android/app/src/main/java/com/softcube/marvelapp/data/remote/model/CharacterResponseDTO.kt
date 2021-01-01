package com.softcube.marvelapp.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.softcube.marvelapp.domain.model.Character
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.marvelapp.data.remote.model
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class CharacterResponseDTO(
	@SerializedName("id") val id: Int,
	@SerializedName("name") val name: String,
	@SerializedName("description") val description: String,
	@SerializedName("modified") val modified: String,
	@SerializedName("thumbnail") val thumbnail: Thumbnail,
	@SerializedName("resourceURI") val resourceURI: String,
	@SerializedName("comics") val comics: Collection,
	@SerializedName("stories") val stories: Collection,
	@SerializedName("events") val events: Collection,
	@SerializedName("urls") val urls: List<Url>,
) : Entity<Character> {

	override fun toDomain(): Character {
		return Character(
			this.id,
			this.name,
			this.description,
			this.thumbnail,
			this.comics,
			this.stories,
			this.events
		)
	}
}

@Parcelize
data class Thumbnail(
	@SerializedName("path") val path: String,
	@SerializedName("extension") val extension: String
) : Parcelable {
	fun getURL(): String {
		return "${path}.$extension"
	}
}

@Parcelize
data class Collection(
	@SerializedName("available") val available: Int,
	@SerializedName("collectionURI") val collectionURI: String,
	@SerializedName("returned") val returned: Int,
	@SerializedName("items") val items: List<Item>
) : Parcelable

@Parcelize
data class Item(
	@SerializedName("resourceURI") val resourceURI: String,
	@SerializedName("name") val name: String,
	@SerializedName("type") val type: String? = null
) : Parcelable

@Parcelize
data class Url(
	@SerializedName("type") val type: String,
	@SerializedName("url") val url: String
) : Parcelable
