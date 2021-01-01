package com.softcube.marvelapp.application.base

/**
 * com.softcube.marvelapp.application.base
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
interface BaseUseCase<in Parameter, out Result> {
	suspend operator fun invoke(params: Parameter): Result
}

