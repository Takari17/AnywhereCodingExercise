package com.takari.anywherecodingexercise.di

import anywherecodingexercise.DuckDuckGoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideDuckDuckGoService(): DuckDuckGoService {
        return Retrofit.Builder()
            .baseUrl("http://api.duckduckgo.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DuckDuckGoService::class.java)
    }
}
