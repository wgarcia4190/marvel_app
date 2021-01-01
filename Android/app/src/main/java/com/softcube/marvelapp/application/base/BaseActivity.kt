package com.softcube.marvelapp.application.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.softcube.marvelapp.common.NetworkUtils

/**
 * com.softcube.marvelapp.application.base
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
open class BaseActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	protected fun onNetworkChange(block: (Boolean) -> Unit) {
		NetworkUtils.getNetworkStatus(this)
			.observe(
				this,
				Observer { isConnected ->
					block(isConnected)
				}
			)
	}
}

