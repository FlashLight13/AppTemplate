package com.example.apptemplate.payment.domain.entities

import com.example.apptemplate.payment.domain.rest.entities.PaymentMethodResponse

data class PaymentMethod(
    val label: String,
    val logo: String,
)