package cl.cotemustis.rickandmorty.di

import cl.cotemustis.rickandmorty.BuildConfig
import cl.cotemustis.rickandmorty.data.api.ApiConstants
import cl.cotemustis.rickandmorty.data.api.RMHttpInterceptor
import cl.cotemustis.rickandmorty.data.api.RickMortyApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RickMortyApiModule {

    companion object {
        private const val CONNECT_TIMEOUT = 60L
        private const val READ_TIMEOUT = 60L
        private const val WRITE_TIMEOUT = 120L
        private var BASE_ENDPOINT: String = ""
    }

    @Singleton
    @Provides
    fun providesMoshi() = Moshi.Builder().build()

    @Provides
    @Singleton
    @Named("provideRMHttpInterceptor")
    fun provideInterceptor(): RMHttpInterceptor {

//        Within this provider we can create a custom Interceptor that will handle session vars, tokens
//        among other things.
        return RMHttpInterceptor()
    }

    @Provides
    @Singleton
    @Named("provideRetrofitHttpClient")
    fun provideHttpClient(@Named("provideRMHttpInterceptor") rmHttpInterceptor: RMHttpInterceptor)
            : OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.addInterceptor(rmHttpInterceptor)

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    @Named("provideRMRetrofit")
    fun provideRetrofit(
        @Named("provideRetrofitHttpClient") okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    fun provideRickMortiAPI(@Named("provideRMRetrofit") retrofit: Retrofit): RickMortyApi =
        retrofit.create(RickMortyApi::class.java)
}