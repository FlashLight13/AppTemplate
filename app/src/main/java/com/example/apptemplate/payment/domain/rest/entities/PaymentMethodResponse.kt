package com.example.apptemplate.payment.domain.rest.entities

import com.example.apptemplate.payment.domain.entities.PaymentMethod
import com.squareup.moshi.Json

data class PaymentMethodResponse(
    @Json(name = "label") val label: String,
    @Json(name = "links") val links: Links,
) {

    data class Links(@Json(name = "logo") val logo: String)
}

fun PaymentMethodResponse.toDomain(): PaymentMethod = PaymentMethod(label, links.logo)