package com.softcube.marvelapp.application.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.softcube.marvelapp.R
import com.softcube.marvelapp.databinding.ComicListItemBinding
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.domain.model.Comic

/**
 * com.softcube.marvelapp.application.adapter
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
typealias ComicCallback = (Comic) -> Unit

class ComicsAdapter(private val comicCallback: ComicCallback) : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

	private val items = mutableListOf<Comic>()

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ComicsViewHolder {
		val binding =
			DataBindingUtil.inflate<ComicListItemBinding>(
				LayoutInflater.from(parent.context),
				R.layout.comic_list_item,
				parent,
				false
			)
		return ComicsViewHolder(binding).apply {
			binding.root.setOnClickListener {
				val position =
					adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener

				comicCallback(getComic(position))
			}
		}
	}

	fun updateComicsList(comics : List<Comic>) {
		items.addAll(comics)
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int = items.size

	override fun onBindViewHolder(
		holder: ComicsViewHolder,
		position: Int
	) {
		holder.binding.apply {
			comic = items[position]
			executePendingBindings()
		}
	}

	private fun getComic(index: Int): Comic = items[index]

	class ComicsViewHolder(val binding: ComicListItemBinding) :
		RecyclerView.ViewHolder(binding.root)
}
