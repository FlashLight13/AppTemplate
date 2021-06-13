package com.example.apptemplate.domain.rest

import android.app.Application
import com.example.apptemplate.R
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ErrorFormats @Inject constructor(private val app: Application) {

    fun getErrorMessage(throwable: Throwable?): String = when (throwable) {
        is HttpException -> {
            when (throwable.code()) {
                404 -> app.getString(R.string.error_empty)
                500 -> app.getString(R.string.error_server_error)
                else -> app.getString(R.string.error_unknown)
            }
        }
        is IOException -> {
            app.getString(R.string.error_no_network)
        }
        else -> app.getString(R.string.error_unknown)
    }
}