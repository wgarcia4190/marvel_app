package com.softcube.marvelapp.data.remote.model

import android.os.Parcelable

/**
 * com.softcube.marvelapp.data.remote.model
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
interface Entity<S>: Parcelable {
	fun toDomain(): S
}
