package com.example.apptemplate.payment.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.apptemplate.payment.domain.PaymentMethodsPagingSource
import javax.inject.Provider

class PaymentMethodsViewModel(pagingSource: Provider<PaymentMethodsPagingSource>) : ViewModel() {

    private val pager = Pager(PagingConfig(Int.MAX_VALUE), Unit) { pagingSource.get() }

    val paymentMethods by lazy {
        pager.flow.cachedIn(this.viewModelScope)
    }

    init {
        viewModelScope
    }
}