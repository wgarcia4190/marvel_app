package com.softcube.marvelapp.application.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.softcube.marvelapp.common.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest

/**
 * com.softcube.marvelapp.application.base
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
abstract class BaseViewModelTest : AutoCloseKoinTest() {

	@get:Rule
	open val instantExecutorRule = InstantTaskExecutorRule()

	@ExperimentalCoroutinesApi
	@get:Rule
	open val coroutineTestRule = CoroutineTestRule()

	abstract fun prepareViewModel(state: State)
}
