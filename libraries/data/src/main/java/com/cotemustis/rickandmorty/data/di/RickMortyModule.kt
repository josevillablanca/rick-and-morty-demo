package com.cotemustis.rickandmorty.data.di

import com.cotemustis.rickandmorty.data.api.CharactersRemoteDataSource
import com.cotemustis.rickandmorty.data.api.RemoteDataSource
import com.cotemustis.rickandmorty.data.api.RickMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RickMortyModule {

//    @Singleton
//    @Provides
//    fun provideRMRepositoryImpl(
//        remoteDataSource: CharactersRemoteDataSource
//    ) = RmRepositoryImpl(remoteDataSource) as RmRepository

    @Singleton
    @Provides
    fun provideCharactersRemoteDataSource(
        api: RickMortyApi
    ) = CharactersRemoteDataSource(api) as RemoteDataSource



}