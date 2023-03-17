package com.cotemustis.rickandmorty.data.api

import com.cotemustis.rickandmorty.data.model.CharacterData
import com.cotemustis.rickandmorty.data.model.CharactersResponseData

interface RemoteDataSource {
    suspend fun getCharacters(): CharactersResponseData
    suspend fun getCharacterById(id: Int): CharacterData
}