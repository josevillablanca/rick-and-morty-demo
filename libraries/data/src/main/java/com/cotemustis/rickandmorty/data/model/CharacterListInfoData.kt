package com.cotemustis.rickandmorty.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterListInfoData(

    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "pages")
    val pages: Int = 0,
    @Json(name = "next")
    val next: String? = null,
    @Json(name = "prev")
    val prev: String? = null
)
