package com.softcube.marvelapp.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import com.softcube.marvelapp.application.base.BaseDialog
import com.softcube.marvelapp.common.autoCleared
import com.softcube.marvelapp.databinding.DialogErrorBinding

/**
 * com.softcube.marvelapp.presentation.dialog
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
class ErrorDialog : BaseDialog() {

	private var binding by autoCleared<DialogErrorBinding>()
	private lateinit var title: String
	private lateinit var subTitle: String

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		binding = DialogErrorBinding.inflate(LayoutInflater.from(context))

		val builder: AlertDialog.Builder = AlertDialog.Builder(context)
		builder.setView(binding.root)

		binding.lifecycleOwner = this
		binding.clickHandler = ClickHandler()

		isCancelable = false

		if (::title.isInitialized && ::subTitle.isInitialized) {
			binding.message.text = title
			binding.subTitle.text = subTitle
		}

		return builder.create()
	}

	fun setData(title: String, subTitle: String) {
		this.title = title
		this.subTitle = subTitle
	}

	inner class ClickHandler {
		fun close() {
			dismiss()
		}
	}
}
