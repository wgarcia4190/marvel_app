package com.softcube.marvelapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.marvelapp.data.remote.model
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class ComicRequestDTO(
	val characterId: Long,
	val limit: Int = 20,
	val offset: Int = 0
): Parcelable
