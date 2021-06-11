package com.example.apptemplate.payment.domain.rest.entities

import com.squareup.moshi.Json

data class PaymentMethodsResponse(@Json(name = "networks") val networks: Networks) {

    data class Networks(@Json(name = "applicable") val applicable: List<PaymentMethodResponse>)
}