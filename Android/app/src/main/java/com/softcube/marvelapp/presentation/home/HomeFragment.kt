package com.softcube.marvelapp.presentation.home

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import com.softcube.marvelapp.R
import com.softcube.marvelapp.application.Status
import com.softcube.marvelapp.application.adapter.CharacterAdapter
import com.softcube.marvelapp.application.base.BaseFragment
import com.softcube.marvelapp.common.Log
import com.softcube.marvelapp.common.autoCleared
import com.softcube.marvelapp.common.extension.whatIfNotNullAs
import com.softcube.marvelapp.databinding.FragmentHomeBinding
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.InfinityScrollEntity
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * com.softcube.marvelapp.presentation.home
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
class HomeFragment : BaseFragment() {

	private var binding by autoCleared<FragmentHomeBinding>()
	private val homeViewModel: HomeViewModel by viewModel()

	private var currentPage: Int = 0
	private var pages: Int = 1
	private var totalItemsPerPage: Int = 20
	private var lastItemPosition: Int = 0

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHomeBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = this

		binding.characterList.apply {
			adapter = CharacterAdapter{ character ->
				navigateTo(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(character.id.toLong()))
			}

			addScrollStateChangeListener(object: DiscreteScrollView.ScrollStateChangeListener<CharacterAdapter.CharacterViewHolder>{
				override fun onScrollStart(p0: CharacterAdapter.CharacterViewHolder, p1: Int) {}

				override fun onScrollEnd(vh: CharacterAdapter.CharacterViewHolder, position: Int) {
					if(position == (lastItemPosition - 1) && currentPage < pages)
						homeViewModel.getCharacters(limit = totalItemsPerPage, offset = lastItemPosition)
				}

				override fun onScroll(
					p0: Float,
					p1: Int,
					p2: Int,
					p3: CharacterAdapter.CharacterViewHolder?,
					p4: CharacterAdapter.CharacterViewHolder?
				) {}

			})
		}

		observeHomeViewState()

		homeViewModel.getCharacters()

		return binding.root
	}

	private fun setupAdapter(data: InfinityScrollEntity<Character>) {
		if (data.data.isEmpty()) return

		currentPage += 1
		pages = data.total / totalItemsPerPage
		lastItemPosition = currentPage * totalItemsPerPage

		binding.characterList.apply {
			adapter.whatIfNotNullAs<CharacterAdapter> {
				it.updateCharacterList(data.data)
			}

			setItemTransformer(
				ScaleTransformer.Builder()
					.setMaxScale(1.25f)
					.setMinScale(0.8f)
					.build()
			)
		}
	}

	private fun observeHomeViewState() {
		homeViewModel.homeViewState.observe(
			viewLifecycleOwner,
			Observer { state ->
				when (state.status) {
					Status.NONE -> {
					}
					Status.LOADING -> showLoading()
					Status.SUCCESS -> {
						state.characters?.let { characters ->
							hideLoading {
								setupAdapter(characters)
							}
						}
					}
					Status.ERROR -> {
						hideLoading {
							showError(state.error)
						}
					}
				}
			}
		)
	}
}
