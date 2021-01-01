package com.softcube.marvelapp.common.extension

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * com.softcube.marvelapp.common.extension
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */

internal fun View.show() {
	this.visibility = View.VISIBLE
}

internal fun View.remove() {
	this.visibility = View.GONE
}

val Context.layoutInflater: LayoutInflater
	get() = LayoutInflater.from(this)

internal fun ViewGroup.inflateIntoSelf(@LayoutRes resId: Int, attachToSelf: Boolean): View {
	return context.layoutInflater.inflate(resId, this, attachToSelf)
}
