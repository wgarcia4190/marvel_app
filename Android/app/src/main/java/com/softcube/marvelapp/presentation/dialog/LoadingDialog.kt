package com.softcube.marvelapp.presentation.dialog

import android.content.Context
import com.softcube.marvelapp.R
import com.softcube.marvelapp.application.base.MarvelDialog
import com.softcube.marvelapp.databinding.DialogLoadingBinding

/**
 * com.softcube.marvelapp.presentation.dialog
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
class LoadingDialog(context: Context) :
	MarvelDialog<DialogLoadingBinding>(context, R.layout.dialog_loading)
