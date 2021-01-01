package com.softcube.marvelapp.application.di

import com.softcube.marvelapp.application.AppConfiguration
import com.softcube.marvelapp.data.remote.api.MarvelService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * com.softcube.marvelapp.application.di
 *
 * Created by Wilson Garcia on 12/29/20
 * Copyright © 2020 Wilson Garcia. All rights reserved.
 */
val networkModule = module {
	single { provideRetrofit(okHttpClient = get(), url = AppConfiguration.apiBaseURL) }
	single { provideOkHttpClient() }
	single { provideAuthenticationService(retrofit = get()) }
}

internal fun provideOkHttpClient(): OkHttpClient {
	val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
		level = HttpLoggingInterceptor.Level.BODY
	}
	return OkHttpClient.Builder()
		.connectTimeout(60L, TimeUnit.SECONDS)
		.readTimeout(60L, TimeUnit.SECONDS)
		.addInterceptor(httpLoggingInterceptor)
		.build()
}

internal fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
	return Retrofit.Builder()
		.baseUrl(url)
		.client(okHttpClient)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
}

internal fun provideAuthenticationService(retrofit: Retrofit): MarvelService =
	retrofit.create(MarvelService::class.java)
