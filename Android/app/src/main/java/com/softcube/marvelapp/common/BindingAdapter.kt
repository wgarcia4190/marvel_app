package com.softcube.marvelapp.common

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.softcube.marvelapp.data.remote.model.Thumbnail

/**
 * com.softcube.marvelapp.common
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
object BindingAdapter {
	@JvmStatic
	@BindingAdapter("loadImage")
	fun bindLoadImage(view: AppCompatImageView, thumbnail: Thumbnail) {
		Glide.with(view.context)
			.load(thumbnail.getURL())
			.into(view)
	}
}
