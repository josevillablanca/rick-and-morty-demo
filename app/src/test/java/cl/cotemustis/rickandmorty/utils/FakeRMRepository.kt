package cl.cotemustis.rickandmorty.utils

import cl.cotemustis.rickandmorty.data.model.CharacterData
import cl.cotemustis.rickandmorty.data.model.CharactersResponseData
import cl.cotemustis.rickandmorty.data.utils.Resource
import cl.cotemustis.rickandmorty.repository.RMRepository

class FakeRMRepository : RMRepository{

    private var willReturnError = false

    fun setWillReturnError(value: Boolean){
        willReturnError = value
    }

    override suspend fun getCharacters(): Resource<CharactersResponseData> {
        return if(willReturnError) {
            Resource.error("Error", null)
        } else {
            Resource.success(CharactersResponseData())
        }
    }

    override suspend fun getCharacterById(id: Int): Resource<CharacterData> {
        return if(willReturnError) {
            Resource.error("Error", null)
        } else {
            Resource.success(CharacterData())
        }
    }
}