package com.example.apptemplate.payment.domain.rest

import com.example.apptemplate.payment.domain.rest.entities.PaymentMethodsResponse
import retrofit2.http.GET

interface PaymentMethodsRest {

    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    suspend fun getPaymentMethods(): PaymentMethodsResponse
}