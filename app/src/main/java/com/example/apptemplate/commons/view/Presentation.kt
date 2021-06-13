package com.example.apptemplate.commons.view

import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

interface Presentation<A : Any, V : ViewBinding> {

    fun attach(viewBinding: V, args: A) = Unit
    fun detach(viewBinding: V) = Unit
}

abstract class BasePresentation<A : Any, V : ViewBinding> : Presentation<A, V> {

    protected val presentationScope: CoroutineScope = MainScope()

    final override fun attach(viewBinding: V, args: A) {
        viewBinding.performAttach(args)
    }

    final override fun detach(viewBinding: V) {
        presentationScope.cancel()
        viewBinding.performDetach()
    }

    protected open fun V.performAttach(args: A) = Unit

    protected open fun V.performDetach() = Unit
}