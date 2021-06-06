package cl.cotemustis.rickandmorty.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterData(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "status")
    val status: String = "",
    @Json(name = "species")
    val species: String = "",
    @Json(name = "gender")
    val gender: String = "",
    @Json(name = "origin")
    val characterOrigin: CharacterOriginData? = null,
    @Json(name = "location")
    val location: CharacterLocationData? = null,
    @Json(name = "image")
    val image: String = "",
    @Json(name = "episode")
    val episode: List<String>? = null,
    @Json(name = "url")
    val url: String = "",
    @Json(name = "created")
    val created: String = ""
)
