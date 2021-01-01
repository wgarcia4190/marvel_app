package com.softcube.marvelapp.application.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.softcube.marvelapp.R

/**
 * com.softcube.marvelapp.application.base
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
open class MarvelDialog<T>(context: Context, val layout: Int) :
	Dialog(context) where T : ViewDataBinding {

	private var binding: T =
		DataBindingUtil.inflate(LayoutInflater.from(context), layout, null, false)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		requestWindowFeature(Window.FEATURE_NO_TITLE)

		setContentView(binding.root)
		setCancelable(false)

		window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
		window?.setGravity(Gravity.CENTER)
		window?.setBackgroundDrawableResource(R.color.translucent)
		window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
	}

	override fun show() {
		if (context is Activity) {
			if ((context as Activity).isFinishing)
				return
		}
		super.show()
	}
}
