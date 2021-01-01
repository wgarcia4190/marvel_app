package com.softcube.marvelapp.common.extension

import androidx.lifecycle.LiveData
import com.softcube.marvelapp.common.OneTimeObserver

/**
 * com.softcube.marvelapp.common.extension
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
	val observer = OneTimeObserver(onChangeHandler)
	// Lifecycle owner and observer
	observe(observer, observer)
}
