package com.ejavinas.trackbyitunes.itunessearch.di

import android.content.Context
import com.ejavinas.trackbyitunes.itunessearch.api.ItunesSearchService
import com.ejavinas.trackbyitunes.itunessearch.repository.TrackRepository
import com.ejavinas.trackbyitunes.itunessearch.repository.TrackRepositoryImpl
import com.ejavinas.trackbyitunes.itunessearch.repository.local.TrackLocalDataSource
import com.ejavinas.trackbyitunes.itunessearch.repository.local.TrackLocalDataSourceImpl
import com.ejavinas.trackbyitunes.itunessearch.repository.local.room.ItunesSearchDatabase
import com.ejavinas.trackbyitunes.itunessearch.repository.remote.TrackRemoteDataSource
import com.ejavinas.trackbyitunes.itunessearch.repository.remote.TrackRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for repository-related classes
 */
@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideItunesSearchDatabase(@ApplicationContext context: Context): ItunesSearchDatabase {
        return ItunesSearchDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideTrackLocalDataSource(itunesSearchDatabase: ItunesSearchDatabase): TrackLocalDataSource {
        return TrackLocalDataSourceImpl(itunesSearchDatabase)
    }

    @Singleton
    @Provides
    fun provideAnimeFactsService(): ItunesSearchService {
        return ItunesSearchService.create()
    }

    @Singleton
    @Provides
    fun provideTrackRemoteDataSource(itunesSearchService: ItunesSearchService): TrackRemoteDataSource {
        return TrackRemoteDataSourceImpl(itunesSearchService)
    }

    @Singleton
    @Provides
    fun provideTrackRepository(
        trackLocalDataSource: TrackLocalDataSource,
        trackRemoteDataSource: TrackRemoteDataSource
    ): TrackRepository {
        return TrackRepositoryImpl(trackLocalDataSource, trackRemoteDataSource)
    }

}