package com.rafael.bankai.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val ANIME_ID = 269
private const val JIKAN_BASE_URL =
    "https://api.jikan.moe/v4/anime/${ANIME_ID}/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using to parse JSON
 * */
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

/**
 * Build a Retrofit object with the Moshi converter
 * */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(JIKAN_BASE_URL)
    .build()

interface BankaiApiService {
    @GET("full")
    suspend fun getInformation(): BleachData

    @GET("characters")
    suspend fun getCharacters(): CharactersData
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object BankaiApi {
    val retrofitService: BankaiApiService by lazy { retrofit.create(BankaiApiService::class.java) }
}