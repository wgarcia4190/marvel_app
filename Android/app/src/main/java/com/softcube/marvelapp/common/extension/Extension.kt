package com.softcube.marvelapp.common.extension

/**
 * com.softcube.marvelapp.common.extension
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */

inline fun String?.guardEmpty(block: String?.() -> Unit): String {
	if (isNullOrBlank()) {
		block()
	} else {
		return this
	}

	return ""
}

inline fun <reified R> Any?.whatIfNotNullAs(
	whatIf: (R) -> Unit,
	whatIfNot: () -> Unit
): Any? {

	if (this != null && this is R) {
		whatIf(this as R)
		return this
	}
	whatIfNot()
	return this
}

inline fun <reified R> Any?.whatIfNotNullAs(
	whatIf: (R) -> Unit
): Any? {

	return whatIfNotNullAs(
		whatIf = whatIf,
		whatIfNot = { }
	)
}
