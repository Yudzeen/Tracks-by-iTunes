package com.ejavinas.trackbyitunes.common.rxjava

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * A utility base class for [AndroidViewModel] which perform rxjava operations.
 */
abstract class RxAndroidViewModel(
    application: Application
): AndroidViewModel(application) {

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