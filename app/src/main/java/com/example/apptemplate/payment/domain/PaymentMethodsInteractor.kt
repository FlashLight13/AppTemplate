package com.example.apptemplate.payment.domain

import com.example.apptemplate.payment.domain.entities.PaymentMethod
import com.example.apptemplate.payment.domain.entities.toDomain
import com.example.apptemplate.payment.domain.rest.PaymentMethodsRest
import javax.inject.Inject

class PaymentMethodsInteractor @Inject constructor(private val rest: PaymentMethodsRest) {

    suspend fun getPaymentMethods(): List<PaymentMethod> {
        val response = rest.getPaymentMethods()
        return response.networks.applicable.map { it.toDomain() }
    }
}