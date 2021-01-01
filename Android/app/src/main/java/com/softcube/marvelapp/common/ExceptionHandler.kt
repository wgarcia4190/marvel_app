package com.softcube.marvelapp.common

import com.softcube.marvelapp.common.extension.guardEmpty
import com.softcube.marvelapp.domain.exception.ApiError
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

const val UNKNOWN_ERROR_MESSAGE = "Unknown Error!"

/**
 * com.softcube.marvelapp.common
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
internal object ExceptionHandler {

	fun parse(throwable: Throwable?): ApiError {
		return when (throwable) {
			is ApiError -> throwable

			is HttpException -> {
				convertErrorBody(throwable)
			}

			is SocketTimeoutException -> {
				ApiError(throwable, throwable.message, null)
			}

			is IOException -> {
				ApiError(throwable, throwable.message, null)
			}

			else -> ApiError(
				throwable, UNKNOWN_ERROR_MESSAGE, null
			)
		}
	}

	private fun convertErrorBody(throwable: HttpException): ApiError {
		val jsonError = throwable.response()?.errorBody()?.string().guardEmpty {
			return ApiError(
				message = UNKNOWN_ERROR_MESSAGE,
				code = UNKNOWN_ERROR_MESSAGE,
				cause = throwable
			)
		}

		val jsonObject = JSONObject(jsonError)

		return ApiError(
			code = jsonObject.optString("code"),
			cause = throwable,
			message = jsonObject.optString("message")
		)
	}
}
