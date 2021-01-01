package com.softcube.marvelapp.application.base

import com.softcube.marvelapp.domain.exception.ApiError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * com.softcube.marvelapp.application.base
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
abstract class BaseTestUseCase<in P, out T>(private val state: State) {

	fun execute(params: P): Flow<T> = flow {
		when (state) {
			State.SUCCESS -> emit(getValue(params))
			State.ERROR -> throw ApiError(null, "Something went wrong", code = null)
		}
	}

	abstract fun getValue(params: P): T
}
