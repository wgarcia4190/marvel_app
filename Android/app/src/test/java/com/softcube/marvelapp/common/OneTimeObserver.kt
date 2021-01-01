package com.softcube.marvelapp.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer

/**
 * com.softcube.marvelapp.common
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
internal class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {

	private val lifecycle = LifecycleRegistry(this)

	init {
		lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
	}

	override fun getLifecycle(): Lifecycle = lifecycle

	override fun onChanged(t: T) {
		handler(t)
		lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	}
}
