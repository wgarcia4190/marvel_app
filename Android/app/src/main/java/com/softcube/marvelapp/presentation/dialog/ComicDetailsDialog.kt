package com.softcube.marvelapp.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import com.softcube.marvelapp.application.base.BaseDialog
import com.softcube.marvelapp.common.autoCleared
import com.softcube.marvelapp.databinding.DialogComicBinding
import com.softcube.marvelapp.databinding.DialogErrorBinding
import com.softcube.marvelapp.domain.model.Comic

/**
 * com.softcube.marvelapp.presentation.dialog
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class ComicDetailsDialog(private val comic: Comic) : BaseDialog() {

	private var binding by autoCleared<DialogComicBinding>()

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		binding = DialogComicBinding.inflate(LayoutInflater.from(context))

		val builder: AlertDialog.Builder = AlertDialog.Builder(context)
		builder.setView(binding.root)

		binding.lifecycleOwner = this
		binding.comic = comic

		isCancelable = true

		return builder.create()
	}
}
