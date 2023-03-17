package com.cotemustis.rickandmorty.domain.repository

import com.cotemustis.rickandmorty.data.model.CharacterData
import com.cotemustis.rickandmorty.data.model.CharactersResponseData
import com.cotemustis.rickandmorty.data.utils.Resource

interface RmRepository {
    suspend fun getCharacters(): Resource<CharactersResponseData>
    suspend fun getCharacterById(id: Int): Resource<CharacterData>
}