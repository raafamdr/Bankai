package com.rafael.bankai.network

/**
 * Info JSON Model
 */
data class BleachData(
    val data: BleachInfo
)

data class BleachInfo(
    val url: String,
    val title: String,
    val type: String,
    val source: String,
    val episodes: Int,
    val status: String,
    val aired: Aired,
    val duration: String,
    val rating: String,
    val score: Float,
    val year: Int,
    val producers: List<Producers>,
    val licensors: List<Licensors>,
    val genres: List<Genres>,
    val theme: Theme,
    val streaming: List<Streaming>
)

data class Aired(
    val from: String,
    val to: String,
    val prop: AiredProperties,
    val string: String
)

data class AiredProperties(
    val from: DateProperties,
    val to: DateProperties
)

data class DateProperties(
    val day: Int,
    val month: Int,
    val year: Int
)

data class Producers(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Licensors(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Genres(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Theme(
    val openings: List<String>,
    val endings: List<String>
)

data class Streaming(
    val name: String,
    val url: String
)

/**
 * Characters JSON Model
 */
data class CharactersData(
    val data: List<Character>
)

data class Character(
    val character: CharacterInfo,
    val role: String,
)

data class CharacterInfo(
    val mal_id: Int,
    val url: String,
    val images: CharacterImages,
    val name: String
)

data class CharacterImages(
    val jpg: ImageUrl,
)

data class ImageUrl(
    val image_url: String
)

/**
 * Quote JSON Model
 */
data class Quote(
    val anime: String,
    val character: String,
    val quote: String
)