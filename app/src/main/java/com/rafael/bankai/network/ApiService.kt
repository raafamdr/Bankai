package com.rafael.bankai.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val ANIME_ID = 269
private const val JIKAN_BASE_URL = "https://api.jikan.moe/v4/anime/${ANIME_ID}/"

private const val ANIME_TITLE = "bleach"
private const val ANIME_CHAN_BASE_URL = "https://animechan.xyz/api/random/anime/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using to parse JSON
 * */
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

/**
 * Build a Retrofit object with the Moshi converter for a given base URL
 */
private fun createRetrofit(baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .build()
}

interface JikanApiService {
    @GET("full")
    suspend fun getInformation(): BleachData

    @GET("characters")
    suspend fun getCharacters(): CharactersData
}

interface AnimeChanApiService {
    @GET("?title=${ANIME_TITLE}")
    suspend fun getQuote(): Quote
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit services
 */
object ApiService {
    // Create Retrofit service for the first API
    private val retrofitJikan = createRetrofit(JIKAN_BASE_URL)
    val jikanApiService: JikanApiService by lazy { retrofitJikan.create(JikanApiService::class.java) }

    // Create Retrofit service for the second API
    private val retrofitAnimeChan = createRetrofit(ANIME_CHAN_BASE_URL)
    val animeChanApiService: AnimeChanApiService by lazy { retrofitAnimeChan.create(AnimeChanApiService::class.java) }
}