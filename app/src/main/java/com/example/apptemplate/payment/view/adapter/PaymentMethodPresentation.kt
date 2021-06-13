package com.example.apptemplate.payment.view.adapter

import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.apptemplate.commons.view.BasePresentation
import com.example.apptemplate.databinding.ViewPaymentMethodBinding
import com.example.apptemplate.payment.domain.entities.PaymentMethod
import javax.inject.Inject

class PaymentMethodPresentation @Inject constructor() :
    BasePresentation<PaymentMethod, ViewPaymentMethodBinding>() {

    override fun ViewPaymentMethodBinding.performAttach(args: PaymentMethod) {
        paymentMethodTitle.text = args.label

        root.setOnClickListener {
            Toast.makeText(it.context, args.label, Toast.LENGTH_SHORT).show()
        }

        Glide.with(paymentMethodIcon.context)
            .asBitmap()
            .load(args.logo)
            .into(paymentMethodIcon)
    }

    override fun ViewPaymentMethodBinding.performDetach() {
        root.setOnClickListener(null)
    }
}
