package com.example.apptemplate.payment.view

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.apptemplate.payment.domain.PaymentMethodsPagingSource
import javax.inject.Provider

class PaymentMethodsViewModel(pagingSource: Provider<PaymentMethodsPagingSource>) : ViewModel() {

    private val pager = Pager(PagingConfig(Int.MAX_VALUE), Unit) { pagingSource.get() }

    val paymentMethods get() = pager.flow

    init {

    }
}