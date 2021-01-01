package com.softcube.marvelapp.viewmodel

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.softcube.marvelapp.application.base.BaseViewModelTest
import com.softcube.marvelapp.application.base.State
import com.softcube.marvelapp.common.extension.observeOnce
import com.softcube.marvelapp.domain.usecase.GetCharactersTestUseCase
import com.softcube.marvelapp.presentation.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * com.softcube.marvelapp.viewmodel
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
class HomeTestViewModel : BaseViewModelTest() {

	private lateinit var homeViewModel: HomeViewModel

	@Test
	fun `when get characters executed return success state`() {
		coroutineTestRule.dispatcher.runBlockingTest {
			prepareViewModel(State.SUCCESS)

			homeViewModel.getCharacters(limit = 2)

			advanceTimeBy(600)

			homeViewModel.homeViewState.observeOnce { state ->
				Truth.assertThat(state.error).isNull()

				val characters = state.characters
				Truth.assertThat(characters).isNotNull()
				Truth.assertThat(characters?.total).isEqualTo(2)
				Truth.assertThat(characters?.data).isNotEmpty()
				Truth.assertThat(characters?.data?.size).isEqualTo(2)
			}
		}
	}

	@Test
	fun `when get characters executed return failed state`() {
		coroutineTestRule.dispatcher.runBlockingTest {
			prepareViewModel(State.ERROR)

			homeViewModel.getCharacters(limit = 2)

			advanceTimeBy(600)

			homeViewModel.homeViewState.observeOnce { state ->
				Truth.assertThat(state.error).isNotNull()

				val characters = state.characters
				Truth.assertThat(characters).isNull()
			}
		}
	}

	override fun prepareViewModel(state: State) {
		val useCase = GetCharactersTestUseCase(state)
		homeViewModel = HomeViewModel(useCase)
	}
}
