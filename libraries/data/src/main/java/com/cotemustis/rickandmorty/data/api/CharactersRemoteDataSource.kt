package com.cotemustis.rickandmorty.data.api

import com.cotemustis.rickandmorty.data.model.CharacterData
import com.cotemustis.rickandmorty.data.model.CharactersResponseData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersRemoteDataSource @Inject constructor(private val api: RickMortyApi):
    RemoteDataSource {

    override suspend fun getCharacters(): CharactersResponseData = api.getCharacters()

    override suspend fun getCharacterById(id: Int): CharacterData = api.getCharacterById(id)
}