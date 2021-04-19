package com.example.apptemplate.arch.presenter

import android.os.Bundle
import com.example.apptemplate.arch.view.ViewHolder

interface Presenter<VH : ViewHolder> {

    fun attach(viewHolder: VH, args: Bundle = Bundle.EMPTY)
    fun detach(viewHolder: VH)
}