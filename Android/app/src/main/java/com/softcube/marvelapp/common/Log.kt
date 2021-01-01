package com.softcube.marvelapp.common

/**
 * com.softcube.marvelapp.common
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
object Log {

	private const val TAG = "MARVEL APP"
	private const val LOG_ENABLE = true
	private const val DETAIL_ENABLE = true

	private fun buildMsg(msg: String, level: Int = android.util.Log.INFO): String {
		val buffer = StringBuilder()
		if (DETAIL_ENABLE) {
			val stackTraceElement = Thread.currentThread().stackTrace[4]
			buffer.append(addLevelIndication(level))
			buffer.append("[ ")
			buffer.append(stackTraceElement.fileName)
			buffer.append(" -- ")
			buffer.append("line: ")
			buffer.append(stackTraceElement.lineNumber)
			buffer.append(" -- ")
			buffer.append(stackTraceElement.methodName)
		}
		buffer.append("() ] _____ ")
		buffer.append("\n========================================================= \n")
		buffer.append(msg)
		buffer.append("\n========================================================= \n")
		return buffer.toString()
	}

	private fun addLevelIndication(level: Int = android.util.Log.INFO): String {
		return when (level) {
			android.util.Log.INFO -> "✅ Info --> "
			android.util.Log.VERBOSE -> "✅ Verbose --> "
			android.util.Log.DEBUG -> "ℹ️ Debug --> "
			android.util.Log.ERROR -> "❌ Error--> "
			android.util.Log.WARN -> "⚠️ Warning--> "
			else -> "ℹ️ Assert --> "
		}
	}

	fun v(msg: String) {
		if (LOG_ENABLE) {
			android.util.Log.v(TAG, buildMsg(msg, android.util.Log.VERBOSE))
		}
	}

	fun d(msg: String) {
		if (LOG_ENABLE) {
			android.util.Log.d(TAG, buildMsg(msg, android.util.Log.DEBUG))
		}
	}

	fun i(msg: String) {
		if (LOG_ENABLE) {
			android.util.Log.i(TAG, buildMsg(msg, android.util.Log.INFO))
		}
	}

	fun w(msg: String) {
		if (LOG_ENABLE) {
			android.util.Log.w(TAG, buildMsg(msg, android.util.Log.WARN))
		}
	}

	fun w(msg: String, e: Exception?) {
		if (LOG_ENABLE) {
			android.util.Log.w(TAG, buildMsg(msg, android.util.Log.WARN), e)
		}
	}

	fun e(msg: String) {
		if (LOG_ENABLE) {
			android.util.Log.e(TAG, buildMsg(msg, android.util.Log.ERROR))
		}
	}

	fun e(msg: String, e: Exception?) {
		if (LOG_ENABLE) {
			android.util.Log.e(TAG, buildMsg(msg), e)
		}
	}
}
