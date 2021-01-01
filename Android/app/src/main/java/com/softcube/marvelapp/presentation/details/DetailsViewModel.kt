package com.softcube.marvelapp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softcube.marvelapp.application.Status
import com.softcube.marvelapp.application.base.BaseViewModel
import com.softcube.marvelapp.common.ExceptionHandler
import com.softcube.marvelapp.data.remote.model.ComicRequestDTO
import com.softcube.marvelapp.domain.exception.ApiError
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.Comic
import com.softcube.marvelapp.domain.model.InfinityScrollEntity
import com.softcube.marvelapp.domain.usecase.GetCharacterBaseUseCase
import com.softcube.marvelapp.domain.usecase.GetComicsBaseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * com.softcube.marvelapp.presentation.details
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
data class DetailsViewState(
	var status: Status,
	var character: Character?,
	var comics: InfinityScrollEntity<Comic>?,
	var error: ApiError?
)

class DetailsViewModel(
	private val useCase: GetCharacterBaseUseCase,
	private val comicsUseCase: GetComicsBaseUseCase
) : BaseViewModel() {

	private var characterJob: Job? = null

	val detailsViewState: LiveData<DetailsViewState>
		get() = _detailsViewState

	private var _detailsViewState = MutableLiveData<DetailsViewState>()

	override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
		val error = ExceptionHandler.parse(exception)
		_detailsViewState.value = _detailsViewState.value?.copy(
			status = Status.ERROR,
			character = null,
			comics = null,
			error = error
		)
	}

	init {
		_detailsViewState.value =
			DetailsViewState(status = Status.NONE, character = null, error = null, comics = null)
	}

	fun getCharacter(id: Long, limit: Int = 20, offset: Int = 0) {
		val request = ComicRequestDTO(id, limit, offset)
		_detailsViewState.value = _detailsViewState.value?.copy(status = Status.LOADING)

		characterJob = launchCoroutine {
			coroutineScope {
				var character: Character? = null
				var comics: InfinityScrollEntity<Comic>? = null
				val characterCall = async { useCase.invoke(id) }
				val comicsCall = async { comicsUseCase.invoke(request) }

				try {
					character = characterCall.await()
					comics = comicsCall.await()
				} catch (ex: Exception) {
					val error = ExceptionHandler.parse(ex)
					_detailsViewState.value = _detailsViewState.value?.copy(
						status = Status.ERROR,
						character = null,
						comics = null,
						error = error
					)
				}

				_detailsViewState.value = _detailsViewState.value?.copy(
					status = Status.SUCCESS,
					character = character,
					comics = comics
				)
			}
		}
	}

	override fun onCleared() {
		super.onCleared()
		characterJob?.cancel()
	}
}
