package com.ejavinas.trackbyitunes.itunessearch.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response for [ItunesSearchService.fetchItems]
 */
@JsonClass(generateAdapter = true)
data class FetchItemsResponse (
    @field:Json(name="resultCount") val resultCount: Int,
    @field:Json(name="results") val results: List<Map<String, Any>>
)
