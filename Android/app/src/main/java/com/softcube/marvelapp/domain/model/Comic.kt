package com.softcube.marvelapp.domain.model

import android.os.Parcelable
import com.softcube.marvelapp.data.remote.model.Thumbnail
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.marvelapp.domain.model
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class Comic(
	val id: Int,
	val title: String,
	val description: String?,
	val thumbnail: Thumbnail?
) : Parcelable
