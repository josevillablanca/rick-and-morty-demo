package cl.cotemustis.rickandmorty.data.api

import cl.cotemustis.rickandmorty.data.model.CharacterData
import cl.cotemustis.rickandmorty.data.model.CharactersResponseData

interface RemoteDataSource {
    suspend fun getCharacters(): CharactersResponseData
    suspend fun getCharacterById(id: Int): CharacterData
}