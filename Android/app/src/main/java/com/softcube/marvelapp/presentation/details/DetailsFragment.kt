package com.softcube.marvelapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.softcube.marvelapp.application.Status
import com.softcube.marvelapp.application.adapter.CharacterAdapter
import com.softcube.marvelapp.application.adapter.ComicsAdapter
import com.softcube.marvelapp.application.base.BaseFragment
import com.softcube.marvelapp.common.BindingAdapter
import com.softcube.marvelapp.common.Log
import com.softcube.marvelapp.common.autoCleared
import com.softcube.marvelapp.common.extension.remove
import com.softcube.marvelapp.common.extension.show
import com.softcube.marvelapp.common.extension.whatIfNotNullAs
import com.softcube.marvelapp.databinding.FragmentDetailsBinding
import com.softcube.marvelapp.databinding.FragmentHomeBinding
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.Comic
import com.softcube.marvelapp.domain.model.InfinityScrollEntity
import com.softcube.marvelapp.presentation.dialog.ComicDetailsDialog
import com.softcube.marvelapp.presentation.home.HomeViewModel
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * com.softcube.marvelapp.presentation.details
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class DetailsFragment: BaseFragment() {

	private var binding by autoCleared<FragmentDetailsBinding>()
	private val safeArgs by navArgs<DetailsFragmentArgs>()
	private val detailsViewModel: DetailsViewModel by viewModel()

	private lateinit var character: Character
	private lateinit var comics: List<Comic>

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentDetailsBinding.inflate(inflater, container, false)
		binding.apply {
			lifecycleOwner = this@DetailsFragment
			handler = ClickHandler()
			comicList.apply {
				adapter = ComicsAdapter{
					showDialog(ComicDetailsDialog(it))
				}
			}
		}

		observeDetailsViewState()

		detailsViewModel.getCharacter(safeArgs.characterId)

		return binding.root
	}

	private fun observeDetailsViewState() {
		detailsViewModel.detailsViewState.observe(
			viewLifecycleOwner,
			Observer { state ->
				when (state.status) {
					Status.NONE -> {
					}
					Status.LOADING -> showLoading()
					Status.SUCCESS -> {
						state.character?.let { character ->
							this.character = character
							hideLoading {
								binding.apply {
									BindingAdapter.bindLoadImage(characterImage, character.thumbnail)
									biography.text = character.description
									characterName.text = character.name

									if(character.description.isEmpty()) {
										biography.remove()
										biographyTitle.remove()
									}

									detailsContainer.show()
								}
							}
						}

						state.comics?.let {
							this.comics = it.data
							setupAdapter(this.comics)
						}

					}
					Status.ERROR -> {
						hideLoading {
							showError(state.error)
							state.error?.cause?.printStackTrace()
						}
					}
				}
			}
		)
	}

	private fun setupAdapter(data: List<Comic>) {
		if (data.isEmpty()) return

		binding.comicList.apply {
			adapter.whatIfNotNullAs<ComicsAdapter> {
				it.updateComicsList(data)
			}
		}
	}

	inner class ClickHandler {
		fun goBack() {
			back()
		}
	}
}
