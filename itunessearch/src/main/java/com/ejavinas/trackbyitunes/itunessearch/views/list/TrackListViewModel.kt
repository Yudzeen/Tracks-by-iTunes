package com.ejavinas.trackbyitunes.itunessearch.views.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ejavinas.trackbyitunes.common.lifecycle.Event
import com.ejavinas.trackbyitunes.common.rxjava.RxAndroidViewModel
import com.ejavinas.trackbyitunes.common.utils.Resource
import com.ejavinas.trackbyitunes.common.utils.network.NetworkException
import com.ejavinas.trackbyitunes.common.utils.network.NetworkUtil
import com.ejavinas.trackbyitunes.itunessearch.model.Track
import com.ejavinas.trackbyitunes.itunessearch.repository.TrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TrackListViewModel
@Inject internal constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val trackRepository: TrackRepository
) : RxAndroidViewModel(application) {

    private val tracksLiveData = MutableLiveData<Resource<List<Track>>>()
    private val refreshEventLiveData = MutableLiveData<Event<Resource<Unit>>>()

    init {
        trackRepository.getTracks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { tracksLiveData.value = Resource.Loading() }
            .subscribe({ tracksLiveData.value = Resource.Success(it) },
                { tracksLiveData.value = Resource.Error(it) } )
            .bind()

        refreshTracks(false)
    }

    fun getTracksLiveData(): LiveData<Resource<List<Track>>> = tracksLiveData

    /**
     * Refreshes tracks if network is available
     * @param notifyUi Set to true if the UI needs to know if network is unavailable
     */
    fun refreshTracks(notifyUi: Boolean = true) {
        if (!NetworkUtil.hasNetworkConnection(getApplication())) {
            Log.d(TAG, "No Network connection, not refreshing")
            if (notifyUi) {
                refreshEventLiveData.value = Event(Resource.Error(NetworkException()))
            }
            return
        }
        trackRepository.refreshTracks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { refreshEventLiveData.value = Event(Resource.Loading()) }
            .subscribe({ refreshEventLiveData.value = Event(Resource.Success(Unit)) },
                { refreshEventLiveData.value = Event(Resource.Error(it)) } )
            .bind()
    }

    fun getRefreshEventLiveData(): LiveData<Event<Resource<Unit>>> = refreshEventLiveData

    companion object {
        private const val TAG = "TrackListViewModel"
    }

}