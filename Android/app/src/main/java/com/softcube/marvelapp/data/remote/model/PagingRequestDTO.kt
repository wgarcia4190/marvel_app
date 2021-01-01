package com.softcube.marvelapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.marvelapp.data.remote.model
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class PagingRequestDTO(
	val limit: Int = 20,
	val offset: Int = 0
): Parcelable
