package com.softcube.marvelapp.application.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialogFragment

/**
 * com.softcube.marvelapp.application.base
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
open class BaseDialog: AppCompatDialogFragment() {

	override fun onResume() {
		val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
		params.width = WindowManager.LayoutParams.MATCH_PARENT
		dialog!!.window!!.attributes = params as WindowManager.LayoutParams
		dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		super.onResume()
	}
}
