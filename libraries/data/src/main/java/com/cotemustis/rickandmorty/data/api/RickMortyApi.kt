package com.cotemustis.rickandmorty.data.api

import com.cotemustis.rickandmorty.data.model.CharacterData
import com.cotemustis.rickandmorty.data.model.CharactersResponseData
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyApi {

    //Get characters with coroutine and detail data from the character
    @GET("/api/character")
    suspend fun getCharacters(): CharactersResponseData

    @GET("/api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterData
}