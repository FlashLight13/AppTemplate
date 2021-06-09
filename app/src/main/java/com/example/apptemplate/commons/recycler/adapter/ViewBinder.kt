package com.example.apptemplate.commons.recycler.adapter

import androidx.viewbinding.ViewBinding

interface ViewBinder<V: ViewBinding, T> {

    fun bind(viewHolder: V, data: T)
    fun unbind(viewHolder: V)
}