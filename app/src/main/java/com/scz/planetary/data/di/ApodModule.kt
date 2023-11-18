package com.scz.planetary.data.di

import com.scz.planetary.data.remote.repository.ApodRepositoryImpl
import com.scz.planetary.data.remote.service.ApodService
import com.scz.planetary.domain.repository.ApodRepository
import com.scz.planetary.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApodModule {

    @Provides
    @Singleton
    fun provideApodService(): ApodService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApodService::class.java)

    @Provides
    @Singleton
    fun provideApodRepository(service: ApodService): ApodRepository =
        ApodRepositoryImpl(service)
}