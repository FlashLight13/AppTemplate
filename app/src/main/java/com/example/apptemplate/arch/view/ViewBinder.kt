package com.example.apptemplate.arch.view

interface ViewBinder<T> {

    fun bind(viewHolder: ViewHolder, data: T)
    fun unbind(viewHolder: ViewHolder)
}