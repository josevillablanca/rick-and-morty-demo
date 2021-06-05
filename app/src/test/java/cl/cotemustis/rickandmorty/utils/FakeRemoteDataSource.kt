package cl.cotemustis.rickandmorty.utils

import cl.cotemustis.rickandmorty.data.api.RemoteDataSource
import cl.cotemustis.rickandmorty.data.model.CharacterData
import cl.cotemustis.rickandmorty.data.model.CharactersResponseData

class FakeRemoteDataSource(
    private val charactersResponseData: CharactersResponseData = CharactersResponseData()
): RemoteDataSource{

    override suspend fun getCharacters(): CharactersResponseData {
        return charactersResponseData
    }

    override suspend fun getCharacterById(id: Int): CharacterData {
        return charactersResponseData.characterList?.get(0)!!
    }
}