package com.softcube.marvelapp.domain.model

/**
 * com.softcube.marvelapp.domain.model
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
data class InfinityScrollEntity<S>(
	val total: Int,
	val data: List<S>
)
