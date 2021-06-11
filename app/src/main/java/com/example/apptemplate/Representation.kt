package com.example.apptemplate

import androidx.viewbinding.ViewBinding

interface Representation<V : ViewBinding> {

    fun V.attach() = Unit
    fun detach() = Unit
}