package com.example.apptemplate.utils.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelInjectUtil {

    @Suppress("unchecked_cast")
    inline fun <reified T : ViewModel> on(
        activity: Activity,
        crossinline createViewModel: () -> T
    ): T {
        require(activity is AppCompatActivity) { "Only compat activities are supported" }
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {

            override fun <T : ViewModel?> create(modelClass: Class<T>): T = createViewModel() as T
        }).get(T::class.java)
    }
}