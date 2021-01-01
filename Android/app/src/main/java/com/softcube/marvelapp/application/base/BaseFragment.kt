package com.softcube.marvelapp.application.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.softcube.marvelapp.R
import com.softcube.marvelapp.application.UnitCallback
import com.softcube.marvelapp.domain.exception.ApiError
import com.softcube.marvelapp.presentation.dialog.ErrorDialog
import com.softcube.marvelapp.presentation.dialog.LoadingDialog

/**
 * com.softcube.marvelapp.application.base
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
open class BaseFragment : Fragment() {

	var loadingDialog: LoadingDialog? = null

	fun navigateTo(direction: NavDirections) = findNavController().navigate(direction)
	fun back() = findNavController().popBackStack()

	fun showError(apiError: ApiError?) {
		val errorDialog = ErrorDialog()
		val errorData = getErrorData(apiError)
		errorDialog.setData(errorData.first, errorData.second)
		errorDialog.show(childFragmentManager, "Error Dialog")
	}

	private fun getErrorData(apiError: ApiError?): Pair<String, String> {
		val subTitle = getString(R.string.check_again)
		return Pair(apiError?.message ?: getString(R.string.error_oops_error_occurred), subTitle)
	}

	fun showLoading() {
		this.loadingDialog = LoadingDialog(requireContext())
		loadingDialog?.show()
	}

	fun hideLoading(callback: UnitCallback) {
		if (loadingDialog?.isShowing == true) {
			loadingDialog?.dismiss()
		}
		callback.invoke()
		loadingDialog = null
	}

	fun showDialog(dialog: BaseDialog) {
		dialog.show(childFragmentManager, "Dialog")
	}
}
