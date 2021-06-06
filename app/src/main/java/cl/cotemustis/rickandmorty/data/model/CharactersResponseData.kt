package cl.cotemustis.rickandmorty.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponseData(
    @Json(name = "info")
    val characterListInfoData: CharacterListInfoData? = null,
    @Json(name = "results")
    val characterList: List<CharacterData>? = null
)
