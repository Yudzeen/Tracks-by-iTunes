package com.ejavinas.trackbyitunes.itunessearch.repository.remote

import com.ejavinas.trackbyitunes.itunessearch.api.ItunesSearchService
import com.ejavinas.trackbyitunes.itunessearch.model.Track
import io.reactivex.Single

/**
 * Implementation of [TrackRemoteDataSource] which retrieves data from [ItunesSearchService]
 */
class TrackRemoteDataSourceImpl(
    private val itunesSearchService: ItunesSearchService
) : TrackRemoteDataSource {

    /**
     * [ItunesSearchService.fetchItems] also returns non-track items, so we need to filter those
     * out first before parsing into our [Track] model.
     */
    override fun fetchTracks(): Single<List<Track>> {
        return itunesSearchService.fetchItems().map { response ->
            response.results
                .filter { it["wrapperType"] == "track" }
                .map { Track (
                    trackId = (it["trackId"] as Double).toLong(),
                    trackName = it["trackCensoredName"] as String,
                    artworkUrl = it["artworkUrl100"] as String,
                    price = it["trackPrice"] as Double,
                    genre = it["primaryGenreName"] as String,
                    longDescription = it["longDescription"] as String?)
                }
        }
    }

}