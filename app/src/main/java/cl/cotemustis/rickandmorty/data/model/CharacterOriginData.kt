package cl.cotemustis.rickandmorty.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterOriginData(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "url")
    val url: String = ""
)
