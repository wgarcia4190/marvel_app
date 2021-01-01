package com.softcube.marvelapp.application

import com.softcube.marvelapp.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest



/**
 * com.softcube.marvelapp.application
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
object AppConfiguration {

	val apiBaseURL: String
		get() = BuildConfig.API_BASE_URL

	val publicKey: String
		get() = BuildConfig.PUBLIC_KEY

	private val privateKey: String
		get() = BuildConfig.PRIVATE_KEY

	val ts: String
		get() {
			val tsLong = System.currentTimeMillis() / 1000
			return tsLong.toString()
		}

	fun hash(ts: String): String {
		return md5(ts.plus(privateKey).plus(publicKey))
	}

	private fun md5(input: String): String {
		val md = MessageDigest.getInstance("MD5")
		return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
	}
}
