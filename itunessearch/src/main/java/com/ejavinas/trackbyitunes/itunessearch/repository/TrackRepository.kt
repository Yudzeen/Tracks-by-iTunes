package com.ejavinas.trackbyitunes.itunessearch.repository

import com.ejavinas.trackbyitunes.itunessearch.model.Track
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Interface for the repository layer of [Track] objects
 */
interface TrackRepository {

    fun getTracks(): Flowable<List<Track>>
    fun refreshTracks(): Completable

}