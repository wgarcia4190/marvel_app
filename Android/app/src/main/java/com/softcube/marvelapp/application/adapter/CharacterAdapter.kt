package com.softcube.marvelapp.application.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.softcube.marvelapp.R
import com.softcube.marvelapp.application.UnitCallback
import com.softcube.marvelapp.databinding.CharacterListItemBinding
import com.softcube.marvelapp.domain.model.Character
import com.softcube.marvelapp.generated.callback.OnClickListener

/**
 * com.softcube.marvelapp.application.adapter
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */

typealias CharacterCallback = (Character) -> Unit

class CharacterAdapter(private val onClickListener: CharacterCallback) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

	private val items = mutableListOf<Character>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = DataBindingUtil.inflate<CharacterListItemBinding>(
			inflater,
			R.layout.character_list_item,
			parent,
			false
		)
		return CharacterViewHolder(binding).apply {
			binding.root.setOnClickListener { view ->
				val position =
					adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
						?: return@setOnClickListener

				onClickListener(getCharacter(position))
			}
		}
	}

	fun updateCharacterList(characters: List<Character>) {
		items.addAll(characters)
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int = items.size

	override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
		holder.binding.apply {
			character = items[position]
			executePendingBindings()
		}
	}

	private fun getCharacter(index: Int): Character = items[index]

	class CharacterViewHolder(val binding: CharacterListItemBinding) :
		RecyclerView.ViewHolder(binding.root)
}
