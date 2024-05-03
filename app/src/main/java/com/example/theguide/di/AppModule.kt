package com.example.theguide.di

import android.app.Application
import com.example.theguide.data.manager.LocalUserManagerImpl
import com.example.theguide.data.remote.PlacesAPI
import com.example.theguide.data.repository.PlaceRepositoryImpl
import com.example.theguide.domain.manager.LocalUserManager
import com.example.theguide.domain.repository.PlaceRepository
import com.example.theguide.domain.usecase.appentry.AppEntryUseCases
import com.example.theguide.domain.usecase.appentry.ReadAppEntryUseCase
import com.example.theguide.domain.usecase.appentry.SaveAppEntryUseCase
import com.example.theguide.domain.usecase.place.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntryUseCase = ReadAppEntryUseCase(localUserManager),
        saveAppEntryUseCase = SaveAppEntryUseCase(localUserManager)
    )

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://0wcwhq0q-8000.euw.devtunnels.ms")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePlacesApi(retrofit: Retrofit): PlacesAPI {
        return retrofit.create(PlacesAPI::class.java)
    }

    @Singleton
    @Provides
    fun providePlaceRepository(placesAPI: PlacesAPI): PlaceRepository {
        return PlaceRepositoryImpl(placesAPI)
    }

    @Provides
    fun provideGetUserUseCase(repository: PlaceRepository): GetUserUseCase {
        return GetUserUseCase(repository)
    }


}