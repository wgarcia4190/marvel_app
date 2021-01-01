package com.softcube.marvelapp.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

/**
 * com.softcube.marvelapp.data.remote.model
 *
 * Created by Wilson Garcia on 12/31/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
data class GeneralResponseDTO<T, S>(
	@SerializedName("code") val code: Int,
	@SerializedName("status") val status: String,
	@SerializedName("copyright") val copyright: String,
	@SerializedName("attributionText") val attributionText: String,
	@SerializedName("attributionHTML") val attributionHTML: String,
	@SerializedName("etag") val etag: String,
	@SerializedName("data") val data: Data<T, S>,
) where T: Entity<S>


data class Data<T, S>(
	@SerializedName("offset") val offset: Int,
	@SerializedName("limit") val limit: Int,
	@SerializedName("total") val total: Int,
	@SerializedName("count") val count: Int,
	@SerializedName("results") val results: List<T>,
) where T: Entity<S>

