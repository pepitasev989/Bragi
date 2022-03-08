package com.example.bragitest.di

import com.example.bragitest.data.CommandRepository
import com.example.bragitest.data.ConnectionStateRepository
import com.example.bragitest.data.ConnectionStateSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideConnectionStateSource(): ConnectionStateSource = ConnectionStateSource()

    @Provides
    @Singleton
    fun provideConnectionStateRepository(connectionStateSource: ConnectionStateSource): ConnectionStateRepository =
        ConnectionStateRepository(connectionStateSource)

    @Provides
    @Singleton
    fun provideCommandRepository(): CommandRepository = CommandRepository()
}
