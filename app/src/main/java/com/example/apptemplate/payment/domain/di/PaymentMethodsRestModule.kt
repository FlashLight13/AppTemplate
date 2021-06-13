package com.example.apptemplate.payment.domain.di

import com.example.apptemplate.payment.domain.rest.PaymentMethodsRest
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