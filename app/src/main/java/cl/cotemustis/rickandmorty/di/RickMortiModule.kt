package cl.cotemustis.rickandmorty.di

import cl.cotemustis.rickandmorty.data.api.CharactersRemoteDataSource
import cl.cotemustis.rickandmorty.data.api.RemoteDataSource
import cl.cotemustis.rickandmorty.data.api.RickMortiApi
import cl.cotemustis.rickandmorty.repository.RmRepository
import cl.cotemustis.rickandmorty.repository.RmRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RickMortiModule {

    @Singleton
    @Provides
    fun provideRMRepositoryImpl(
        remoteDataSource: CharactersRemoteDataSource
    ) = RmRepositoryImpl(remoteDataSource) as RmRepository

    @Singleton
    @Provides
    fun provideCharactersRemoteDataSource(
        api: RickMortiApi
    ) = CharactersRemoteDataSource(api) as RemoteDataSource

}