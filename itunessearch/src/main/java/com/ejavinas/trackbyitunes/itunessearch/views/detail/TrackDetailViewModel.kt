package com.ejavinas.trackbyitunes.itunessearch.views.detail

import androidx.lifecycle.SavedStateHandle
import com.ejavinas.trackbyitunes.common.rxjava.RxViewModel
import com.ejavinas.trackbyitunes.itunessearch.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackDetailViewModel
@Inject internal constructor(
    private val savedStateHandle: SavedStateHandle
) : RxViewModel() {

    fun getTrack(): Track {
        return savedStateHandle[TRACK_NAV_ARG_KEY]!!
    }

    companion object {
        private const val TRACK_NAV_ARG_KEY = "track"
    }
}