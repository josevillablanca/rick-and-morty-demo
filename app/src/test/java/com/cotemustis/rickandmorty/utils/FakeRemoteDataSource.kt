package com.cotemustis.rickandmorty.utils

class FakeRemoteDataSource(
    private val charactersResponseData: com.cotemustis.rickandmorty.data.model.CharactersResponseData = com.cotemustis.rickandmorty.data.model.CharactersResponseData()
): com.cotemustis.rickandmorty.data.api.RemoteDataSource {

    override suspend fun getCharacters(): com.cotemustis.rickandmorty.data.model.CharactersResponseData {
        return charactersResponseData
    }

    override suspend fun getCharacterById(id: Int): com.cotemustis.rickandmorty.data.model.CharacterData {
        return charactersResponseData.characterList?.get(0)!!
    }
}