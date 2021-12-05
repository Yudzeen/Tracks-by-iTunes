package com.ejavinas.trackbyitunes.common.rxjava

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * A utility base class for [ViewModel] which perform rxjava operations. This disposes [Disposable]
 * which are added through [bind] method when the [ViewModel] is cleared.
 */
abstract class RxViewModel: ViewModel() {

    private val disposables = CompositeDisposable()

    /**
     * Extension to add a [Disposable] for disposal later on.
     */
    fun Disposable.bind() {
        disposables.add(this)
    }

    /**
     * Dispose [disposables] when [ViewModel] is cleared.
     */
    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}