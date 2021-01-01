package com.softcube.marvelapp.common

import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.UnknownHostException

/**
 * com.softcube.marvelapp.common
 *
 * Created by Wilson Garcia on 1/1/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@RunWith(JUnit4::class)
class ExceptionHandlerTest {

	@Test
	fun `given an exception when is unknown instance then get default string`() {
		val error = ExceptionHandler.parse(Exception())
		Truth.assertThat(error.message).isEqualTo("500")
	}
}
