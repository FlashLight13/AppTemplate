package com.example.apptemplate.payment.domain.rest

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class PaymentMethodsRestModule {

    @Provides
    fun providePaymentMethodsRest(retrofit: Retrofit): PaymentMethodsRest =
        retrofit.create(PaymentMethodsRest::class.java)
}