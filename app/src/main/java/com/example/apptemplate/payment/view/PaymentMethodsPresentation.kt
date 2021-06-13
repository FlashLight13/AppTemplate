package com.example.apptemplate.payment.view

import android.view.View
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apptemplate.R
import com.example.apptemplate.commons.recycler.adapter.PresentationAdapter
import com.example.apptemplate.commons.view.BasePresentation
import com.example.apptemplate.databinding.ViewPaymentMethodsBinding
import com.example.apptemplate.domain.rest.ErrorFormats
import com.example.apptemplate.payment.domain.entities.PaymentMethod
import com.example.apptemplate.payment.view.adapter.PaymentsMethodsAdapterFactory
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScoped
class PaymentMethodsPresentation @Inject constructor(
    paymentsMethodsAdapterFactory: PaymentsMethodsAdapterFactory,
    private val viewModel: PaymentMethodsViewModel,
    private val errorFormats: ErrorFormats,
) : BasePresentation<Unit, ViewPaymentMethodsBinding>() {

    private val presentationAdapter: PresentationAdapter<PaymentMethod> =
        paymentsMethodsAdapterFactory.createAdapter()

    override fun ViewPaymentMethodsBinding.performAttach(args: Unit) {
        paymentMethods.adapter = presentationAdapter
        (paymentMethods.layoutManager as GridLayoutManager).spanCount =
            root.context.resources.getInteger(R.integer.payment_methods_span_count)

        errorRoot.retry.setOnClickListener {
            presentationAdapter.retry()
        }

        presentationScope.launch {
            viewModel.paymentMethods.collect {
                presentationAdapter.submitData(it)
            }
        }

        presentationScope.launch {
            loadStatesFlow().collect {
                when (val state: LoadState = it.refresh) {
                    is LoadState.Loading -> {
                        progressRoot.root.visibility = View.VISIBLE
                        errorRoot.root.visibility = View.GONE
                        paymentMethods.visibility = View.GONE
                    }
                    is LoadState.Error -> {
                        progressRoot.root.visibility = View.GONE
                        errorRoot.root.visibility = View.VISIBLE
                        paymentMethods.visibility = View.GONE

                        errorRoot.errorMessage.text =
                            errorFormats.getErrorMessage(state.error)
                    }
                    is LoadState.NotLoading -> {
                        progressRoot.root.visibility = View.GONE
                        errorRoot.root.visibility = View.GONE
                        paymentMethods.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun loadStatesFlow(): Flow<CombinedLoadStates> =
        callbackFlow {
            val loadStateListener: (CombinedLoadStates) -> Unit = {
                offer(it)
            }
            presentationAdapter.addLoadStateListener(loadStateListener)
            awaitClose { presentationAdapter.removeLoadStateListener(loadStateListener) }
        }

    override fun ViewPaymentMethodsBinding.performDetach() {
        errorRoot.retry.setOnClickListener(null)
        paymentMethods.adapter = null
    }
}