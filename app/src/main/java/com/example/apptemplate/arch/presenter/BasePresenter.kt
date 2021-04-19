package com.example.apptemplate.arch.presenter

import androidx.annotation.CallSuper
import com.example.apptemplate.arch.view.ViewHolder
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BasePresenter<VH: ViewHolder>: Presenter<VH> {

    private val subscriptions: CompositeDisposable = CompositeDisposable()

    @CallSuper
    override fun detach(viewHolder: VH) {
        subscriptions.clear()
    }

    protected fun Disposable.untilDetach() {
        subscriptions.add(this)
    }
}