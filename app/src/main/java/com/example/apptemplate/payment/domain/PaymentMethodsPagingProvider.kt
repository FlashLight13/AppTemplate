package com.example.apptemplate.payment.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.apptemplate.payment.domain.entities.PaymentMethod
import javax.inject.Inject

class PaymentMethodsPagingSource @Inject constructor(private val interactor: PaymentMethodsInteractor) :
    PagingSource<Unit, PaymentMethod>() {

    override fun getRefreshKey(state: PagingState<Unit, PaymentMethod>): Unit = Unit

    override suspend fun load(params: LoadParams<Unit>): LoadResult<Unit, PaymentMethod> =
        LoadResult.Page(interactor.getPaymentMethods(), Unit, Unit)
}