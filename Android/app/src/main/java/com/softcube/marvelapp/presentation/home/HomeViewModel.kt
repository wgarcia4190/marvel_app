package com.softcube.marvelapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softcube.marvelapp.application.Status
import com.softcube.marvelapp.application.base.BaseViewModel
import com.softcube.marvelapp.common.ExceptionHandler
import com.softcube.marvelapp.data.remote.model.PagingRequestDTO
import com.softcube.marvelapp.domain.exception.ApiError
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.InfinityScrollEntity
import com.softcube.marvelapp.domain.usecase.GetCharactersBaseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect

/**
 * com.softcube.marvelapp.presentation.home
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
data class HomeViewState(
	var status: Status,
	var characters: InfinityScrollEntity<Character>?,
	var error: ApiError?
)

class HomeViewModel(private val useCase: GetCharactersBaseUseCase) : BaseViewModel() {

	private var charactersJob: Job? = null

	val homeViewState: LiveData<HomeViewState>
		get() = _homeViewState

	private var _homeViewState = MutableLiveData<HomeViewState>()

	override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
		val error = ExceptionHandler.parse(exception)
		_homeViewState.value = _homeViewState.value?.copy(status = Status.ERROR, characters = null, error = error)
	}

	init {
		_homeViewState.value = HomeViewState(status = Status.NONE, characters = null, error = null)
	}

	fun getCharacters(limit: Int = 20, offset: Int = 0) {
		val request = PagingRequestDTO(limit, offset)
		_homeViewState.value = _homeViewState.value?.copy(status = Status.LOADING)
		charactersJob = launchCoroutine {
			useCase(request).collect {
				_homeViewState.value = _homeViewState.value?.copy(status = Status.SUCCESS, characters = it)
			}
		}
	}

	override fun onCleared() {
		super.onCleared()
		charactersJob?.cancel()
	}
}

