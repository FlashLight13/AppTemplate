package com.example.apptemplate.payment.view.di

import android.app.Activity
import com.example.apptemplate.payment.domain.PaymentMethodsPagingSource
import com.example.apptemplate.payment.view.PaymentMethodsViewModel
import com.example.apptemplate.utils.di.ViewModelInjectUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Provider

@Module
@InstallIn(ActivityComponent::class)
class PaymentMethodsViewModule {

    @Provides
    fun providePaymentMethodsViewModel(
        activity: Activity,
        pagingSourceProvider: Provider<PaymentMethodsPagingSource>,
    ): PaymentMethodsViewModel =
        ViewModelInjectUtil.on(activity) { PaymentMethodsViewModel(pagingSourceProvider) }
}