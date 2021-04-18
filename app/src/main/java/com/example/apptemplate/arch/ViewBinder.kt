package com.example.apptemplate.arch

interface ViewBinder<T> {

    fun bind(viewHolder: ViewHolder, data: T)
    fun unbind(viewHolder: ViewHolder)
}