package com.example.apptemplate.payment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemplate.databinding.ViewPaymentMethodsBinding
import com.example.apptemplate.payment.view.PaymentMethodsPresentation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaymentMethodsActivity : AppCompatActivity() {

    @Inject
    lateinit var representation: PaymentMethodsPresentation

    private lateinit var viewBinding: ViewPaymentMethodsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ViewPaymentMethodsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        representation.attach(viewBinding, Unit)
    }

    override fun onDestroy() {
        representation.detach(viewBinding)
        super.onDestroy()
    }
}