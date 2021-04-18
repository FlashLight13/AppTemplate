package com.example.apptemplate.arch

import android.os.Bundle

interface Presenter {

    fun attach(viewHolder: ViewHolder, args: Bundle = Bundle.EMPTY)
    fun detach(viewHolder: ViewHolder)
}