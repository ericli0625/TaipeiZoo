package com.example.taipeizoo.ui.base

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter(private val repository: BaseRepository) {

    private var disposables = CompositeDisposable()

    val isNetworkConnected: Boolean
        get() = repository.isNetworkConnected

    fun registerObservables() {
        if (!disposables.isDisposed) disposables.dispose()

        disposables = CompositeDisposable()
    }

    fun unRegisterObservables() {
        disposables.dispose()
    }

    fun <T> Observable<T>.subscribeWithAutoDispose() {
        subscribe().let(disposables::add)
    }

    fun <T> Observable<T>.subscribeWithAutoDispose(onNext: (T) -> Unit) {
        subscribe(onNext).let(disposables::add)
    }
}