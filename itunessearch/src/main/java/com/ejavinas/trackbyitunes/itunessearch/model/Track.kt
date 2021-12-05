package com.ejavinas.trackbyitunes.itunessearch.model

import androidx.annotation.Keep
import java.io.Serializable

/**
 * Track POJO
 */
@Keep
data class Track (
    val trackId: Long,
    val trackName: String,
    val artworkUrl: String,
    val price: Double,
    val genre: String,
    val longDescription: String?,
): Serializable