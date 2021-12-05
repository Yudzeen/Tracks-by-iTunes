package com.ejavinas.trackbyitunes.itunessearch.repository.remote

import com.ejavinas.trackbyitunes.itunessearch.model.Track
import io.reactivex.Single

/**
 * Interface for [Track] objects stored remotely
 */
interface TrackRemoteDataSource {

    fun fetchTracks() : Single<List<Track>>

}