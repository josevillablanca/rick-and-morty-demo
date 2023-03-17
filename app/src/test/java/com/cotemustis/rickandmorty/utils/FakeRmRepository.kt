package com.cotemustis.rickandmorty.utils

import com.cotemustis.rickandmorty.data.model.CharacterData
import com.cotemustis.rickandmorty.data.model.CharactersResponseData
import com.cotemustis.rickandmorty.data.utils.Resource
import com.cotemustis.rickandmorty.domain.repository.RmRepository

class FakeRmRepository : RmRepository {

    private var willReturnError = false

    fun setWillReturnError(value: Boolean){
        willReturnError = value
    }

    override suspend fun getCharacters(): Resource<CharactersResponseData> {
        return if(willReturnError) {
            Resource.error("Error", null)
        } else {
            Resource.success(com.cotemustis.rickandmorty.data.model.CharactersResponseData())
        }
    }

    override suspend fun getCharacterById(id: Int): Resource<CharacterData> {
        return if(willReturnError) {
            Resource.error("Error", null)
        } else {
            Resource.success(com.cotemustis.rickandmorty.data.model.CharacterData())
        }
    }
}