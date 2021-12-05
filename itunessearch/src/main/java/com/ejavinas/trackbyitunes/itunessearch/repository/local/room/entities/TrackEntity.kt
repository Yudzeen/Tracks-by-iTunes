package com.ejavinas.trackbyitunes.itunessearch.repository.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room table to store [Track] objects
 */
@Entity(tableName = "track")
data class TrackEntity (

    @PrimaryKey
    @ColumnInfo(name = "track_id")
    val trackId: Long,

    @ColumnInfo(name = "track_name")
    val trackName: String,

    @ColumnInfo(name = "artwork_url")
    val artworkUrl: String,

    val price: Double,

    val genre: String,

    @ColumnInfo(name = "long_description")
    val longDescription: String?,
)