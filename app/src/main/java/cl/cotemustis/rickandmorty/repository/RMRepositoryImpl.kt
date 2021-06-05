package cl.cotemustis.rickandmorty.repository

import android.util.Log
import cl.cotemustis.rickandmorty.data.api.RemoteDataSource
import cl.cotemustis.rickandmorty.data.model.CharacterData
import cl.cotemustis.rickandmorty.data.model.CharactersResponseData
import cl.cotemustis.rickandmorty.data.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RMRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    RMRepository {

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