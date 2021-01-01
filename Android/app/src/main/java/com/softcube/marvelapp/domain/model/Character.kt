package com.softcube.marvelapp.domain.model

import android.os.Parcelable
import com.softcube.marvelapp.data.remote.model.Collection
import com.softcube.marvelapp.data.remote.model.Thumbnail
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.marvelapp.domain.model
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class Character(
	val id: Int,
	val name: String,
	val description: String,
	val thumbnail: Thumbnail,
	val comics: Collection,
	val stories: Collection,
	val events: Collection
) : Parcelable
