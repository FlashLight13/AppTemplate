package com.example.apptemplate.payment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.apptemplate.commons.view.Presentation
import com.example.apptemplate.commons.recycler.adapter.PresentationAdapter
import com.example.apptemplate.databinding.ViewPaymentMethodBinding
import com.example.apptemplate.payment.domain.entities.PaymentMethod
import javax.inject.Inject
import javax.inject.Provider

class PaymentsMethodsAdapterFactory @Inject constructor(private val presentationProvider: Provider<PaymentMethodPresentation>) {

    private val diffCallback by lazy { ItemDiffCallback() }

    fun createAdapter(): PresentationAdapter<PaymentMethod> =
        PresentationAdapter(diffCallback, viewFactories = createViewFactories())

    private fun createViewFactories(): Map<Int, PresentationAdapter.ViewFactory> = mapOf(
        PaymentMethod::class.hashCode() to object : PresentationAdapter.ViewFactory {
            override fun createView(parent: ViewGroup): ViewBinding =
                ViewPaymentMethodBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            override fun createViewBinder(): Presentation<out Any, out ViewBinding> =
                presentationProvider.get()
        }
    )

    private class ItemDiffCallback : DiffUtil.ItemCallback<PaymentMethod>() {

        override fun areItemsTheSame(
            oldItem: PaymentMethod,
            newItem: PaymentMethod,
        ): Boolean = areContentsTheSame(oldItem, newItem)

        override fun areContentsTheSame(
            oldItem: PaymentMethod,
            newItem: PaymentMethod,
        ): Boolean = oldItem == newItem
    }
}