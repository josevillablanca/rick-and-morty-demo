package cl.cotemustis.rickandmorty.di

import android.content.Context
import cl.cotemustis.rickandmorty.R
import cl.cotemustis.rickandmorty.data.api.CharactersRemoteDataSource
import cl.cotemustis.rickandmorty.data.api.RemoteDataSource
import cl.cotemustis.rickandmorty.data.api.RickMortyApi
import cl.cotemustis.rickandmorty.repository.RmRepository
import cl.cotemustis.rickandmorty.repository.RmRepositoryImpl
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RickMortyModule {

    @Singleton
    @Provides
    fun provideRMRepositoryImpl(
        remoteDataSource: CharactersRemoteDataSource
    ) = RmRepositoryImpl(remoteDataSource) as RmRepository

    @Singleton
    @Provides
    fun provideCharactersRemoteDataSource(
        api: RickMortyApi
    ) = CharactersRemoteDataSource(api) as RemoteDataSource

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_broken_image)
            .error(R.drawable.ic_broken_image)
    )

}