package com.cotemustis.rickandmorty.data.repository

import android.util.Log
import com.cotemustis.rickandmorty.data.model.CharacterData
import com.cotemustis.rickandmorty.data.model.CharactersResponseData
import com.cotemustis.rickandmorty.data.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RmRepositoryImpl @Inject constructor(private val remoteDataSource: com.cotemustis.rickandmorty.data.api.RemoteDataSource) :
    RmRepository {

    override suspend fun getCharacters(): Resource<CharactersResponseData> {
        return try {
            val response = remoteDataSource.getCharacters()
            Resource.success(response)
        } catch (e: Exception) {
            Log.e("EXCEPTION", e.localizedMessage ?: "Some unknown error occurred")
            Resource.error(e.localizedMessage ?: "Some unknown error occurred", null)
        }
    }

    override suspend fun getCharacterById(id: Int): Resource<CharacterData> {
        return try {
            val response = remoteDataSource.getCharacterById(id)
            Resource.success(response)
        } catch (e: Exception) {
            Log.e("EXCEPTION", e.localizedMessage ?: "Some unknown error occurred")
            Resource.error(e.localizedMessage ?: "Some unknown error occurred", null)
        }
    }
}