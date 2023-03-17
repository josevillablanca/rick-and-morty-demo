package com.cotemustis.rickandmorty.domain.model

data class CharacterDO(

    val id: Int? = null,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val gender: String = "",
    val characterOrigin: CharacterOriginData? = null,
    val location: CharacterLocationData? = null,
    val image: String = "",
    val episode: List<String>? = null,
    val url: String = "",
    val created: String = ""
)
