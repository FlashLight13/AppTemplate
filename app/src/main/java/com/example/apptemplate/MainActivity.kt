package com.example.apptemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemplate.databinding.ActivityMainBinding
import com.example.apptemplate.payment.view.PaymentMethodsRepresentation
import com.example.apptemplate.payment.view.PaymentMethodsViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var representation: PaymentMethodsRepresentation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        representation.attach(viewBinding)
    }

    override fun onDestroy() {
        representation.detach()
        super.onDestroy()
    }
}