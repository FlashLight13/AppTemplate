package com.example.apptemplate.payment.view

import com.example.apptemplate.Representation
import com.example.apptemplate.databinding.ActivityMainBinding
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PaymentMethodsRepresentation @Inject constructor(private val viewModel: PaymentMethodsViewModel) :
    Representation<ActivityMainBinding> {

    override fun ActivityMainBinding.attach() {

    }
}