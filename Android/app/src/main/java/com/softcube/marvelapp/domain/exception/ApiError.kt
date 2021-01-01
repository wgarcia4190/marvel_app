package com.softcube.marvelapp.domain.exception

/**
 * com.softcube.marvelapp.domain.exception
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
data class ApiError(
	override val cause: Throwable? = null,
	override val message: String?,
	val code: String? = null,
) : Exception(message, cause) {

	override fun toString(): String {
		return "[message: $message - code: $code - cause: ${cause?.message}]"
	}
}
