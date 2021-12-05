package com.ejavinas.trackbyitunes.itunessearch.repository.local.util

import com.ejavinas.trackbyitunes.itunessearch.model.Track
import com.ejavinas.trackbyitunes.itunessearch.repository.local.room.entities.TrackEntity

/**
 * Utility function to easily transform model [Track] to its room entity counterpart [TrackEntity]
 */
fun TrackEntity.toModel(): Track {
    return Track(trackId, trackName, artworkUrl, price, genre, longDescription)
}

/**
 * Utility function to easily transform room entity [TrackEntity] to its model counterpart [Track]
 */
fun Track.toEntity(): TrackEntity {
    return TrackEntity(trackId, trackName, artworkUrl, price, genre, longDescription)
}