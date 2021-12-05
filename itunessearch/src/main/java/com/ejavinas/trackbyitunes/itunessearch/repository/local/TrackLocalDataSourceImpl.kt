package com.ejavinas.trackbyitunes.itunessearch.repository.local

import com.ejavinas.trackbyitunes.itunessearch.model.Track
import com.ejavinas.trackbyitunes.itunessearch.repository.local.room.ItunesSearchDatabase
import com.ejavinas.trackbyitunes.itunessearch.repository.local.util.toEntity
import com.ejavinas.trackbyitunes.itunessearch.repository.local.util.toModel
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Implementation of [TrackLocalDataSource] which retrieves data from [ItunesSearchDatabase]
 */
class TrackLocalDataSourceImpl(
    private val itunesSearchDatabase: ItunesSearchDatabase
) : TrackLocalDataSource {

    override fun getTracks(): Flowable<List<Track>> {
        return itunesSearchDatabase.trackDao().getTracks()
            .map { trackEntities -> trackEntities.map { it.toModel() } }
    }

    override fun insertTracks(tracks: List<Track>): Completable {
        return itunesSearchDatabase.trackDao().insertTracks(tracks.map { it.toEntity() })
    }

    override fun clearTracks(): Completable {
        return itunesSearchDatabase.trackDao().clearTracks()
    }

}