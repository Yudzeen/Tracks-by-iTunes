package com.ejavinas.trackbyitunes.itunessearch.repository

import com.ejavinas.trackbyitunes.itunessearch.model.Track
import com.ejavinas.trackbyitunes.itunessearch.repository.local.TrackLocalDataSource
import com.ejavinas.trackbyitunes.itunessearch.repository.remote.TrackRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Implementation of [TrackRepository] which retrieves data both locally and remotely.
 */
class TrackRepositoryImpl(
    private val trackLocalDataSource: TrackLocalDataSource,
    private val trackRemoteDataSource: TrackRemoteDataSource
) : TrackRepository {

    override fun getTracks(): Flowable<List<Track>> {
        return trackLocalDataSource.getTracks()
    }

    /**
     * Fetches tracks from remote, and store them locally. Previously stored tracks are removed.
     */
    override fun refreshTracks(): Completable {
        return trackRemoteDataSource.fetchTracks()
            .flatMapCompletable {
                trackLocalDataSource.clearTracks()
                    .andThen(trackLocalDataSource.insertTracks(it))
            }
    }

}