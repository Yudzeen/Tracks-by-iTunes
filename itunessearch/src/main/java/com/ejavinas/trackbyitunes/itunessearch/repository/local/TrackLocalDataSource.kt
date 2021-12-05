package com.ejavinas.trackbyitunes.itunessearch.repository.local

import com.ejavinas.trackbyitunes.itunessearch.model.Track
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Interface for [Track] objects stored locally
 */
interface TrackLocalDataSource {

    fun getTracks(): Flowable<List<Track>>
    fun insertTracks(tracks: List<Track>): Completable
    fun clearTracks(): Completable

}