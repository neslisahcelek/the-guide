package com.example.theguide.di

import android.app.Application
import androidx.room.Room
import com.example.theguide.data.local.Migration1To2
import com.example.theguide.data.local.UserDao
import com.example.theguide.data.local.UserDatabase
import com.example.theguide.data.manager.LocalUserManagerImpl
import com.example.theguide.data.remote.PlacesAPI
import com.example.theguide.data.repository.PlaceRepositoryImpl
import com.example.theguide.domain.manager.LocalUserManager
import com.example.theguide.domain.repository.PlaceRepository
import com.example.theguide.domain.usecase.appentry.AppEntryUseCases
import com.example.theguide.domain.usecase.appentry.ReadAppEntryUseCase
import com.example.theguide.domain.usecase.appentry.SaveAppEntryUseCase
import com.example.theguide.domain.usecase.place.AddRatingUseCase
import com.example.theguide.domain.usecase.place.CreateUserUseCase
import com.example.theguide.domain.usecase.place.GetPlaceNameUseCase
import com.example.theguide.domain.usecase.place.GetRecommendationUseCase
import com.example.theguide.domain.usecase.place.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    @Singleton
    @Provides
    fun provideGetUserUseCase(repository: PlaceRepository): GetUserUseCase {
        return GetUserUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetPlaceNameUseCase(repository: PlaceRepository): GetPlaceNameUseCase {
        return GetPlaceNameUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetRecommendationUseCase(repository: PlaceRepository): GetRecommendationUseCase {
        return GetRecommendationUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideCreateUserUseCase(repository: PlaceRepository): CreateUserUseCase {
        return CreateUserUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddRatingUseCase(repository: PlaceRepository): AddRatingUseCase {
        return AddRatingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUserDao(
        userDatabase: UserDatabase
    ): UserDao {
        return userDatabase.userDao
    }

    @Provides
    @Singleton
    fun provideUserDatabase(
        application: Application
    ): UserDatabase {
        return Room.databaseBuilder(
            application,
            UserDatabase::class.java,
            "user_database"
        ).addMigrations(Migration1To2()).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

}