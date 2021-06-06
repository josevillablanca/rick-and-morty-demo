package cl.cotemustis.rickandmorty.repository

import cl.cotemustis.rickandmorty.data.model.CharacterData
import cl.cotemustis.rickandmorty.data.model.CharactersResponseData
import cl.cotemustis.rickandmorty.data.utils.Resource

interface RmRepository {
    suspend fun getCharacters(): Resource<CharactersResponseData>
    suspend fun getCharacterById(id: Int): Resource<CharacterData>
}