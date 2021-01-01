package com.softcube.marvelapp.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.Comic
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.marvelapp.data.remote.model
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class ComicResponseDTO(
	@SerializedName("id") val id: Int,
	@SerializedName("digitalId") val digitalId: Int,
	@SerializedName("title") val title: String,
	@SerializedName("issueNumber") val issueNumber: Int,
	@SerializedName("variantDescription") val variantDescription: String,
	@SerializedName("description") val description: String?,
	@SerializedName("modified") val modified: String?,
	@SerializedName("thumbnail") val thumbnail: Thumbnail?,
	@SerializedName("images") val images: List<Thumbnail>,
) : Entity<Comic> {

	override fun toDomain(): Comic {
		return Comic(
			this.id,
			this.title,
			this.description,
			this.thumbnail
		)
	}
}

