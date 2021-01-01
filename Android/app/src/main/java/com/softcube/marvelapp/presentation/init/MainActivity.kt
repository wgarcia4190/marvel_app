package com.softcube.marvelapp.presentation.init

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.softcube.marvelapp.R
import com.softcube.marvelapp.application.base.BaseActivity

/**
 * com.softcube.marvelapp.presentation.init
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
class MainActivity : BaseActivity() {

	private lateinit var navController: NavController

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		navController = Navigation.findNavController(this, R.id.mainContainer)
	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp() || super.onSupportNavigateUp()
	}
}
