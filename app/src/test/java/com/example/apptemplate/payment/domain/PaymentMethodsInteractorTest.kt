package com.example.apptemplate.payment.domain

import com.example.apptemplate.payment.domain.entities.PaymentMethod
import com.example.apptemplate.payment.domain.rest.PaymentMethodsRest
import com.example.apptemplate.payment.domain.rest.entities.PaymentMethodResponse
import com.example.apptemplate.payment.domain.rest.entities.PaymentMethodsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class PaymentMethodsInteractorTest {

    private val paymentMethodsInteractor by lazy { PaymentMethodsInteractor(rest) }

    @Mock
    private lateinit var rest: PaymentMethodsRest

    @Before
    fun setupTest(): Unit = runBlocking {
        Mockito.`when`(rest.getPaymentMethods()).thenReturn(
            PaymentMethodsResponse(
                PaymentMethodsResponse.Networks(
                    listOf(
                        PaymentMethodResponse(
                            "test_label_1",
                            PaymentMethodResponse.Links("http://test.link.logo/1")
                        ),
                        PaymentMethodResponse(
                            "test_label_2",
                            PaymentMethodResponse.Links("http://test.link.logo/2")
                        ),
                    )
                )
            )
        )
    }

    @Test
    fun `request processing`(): Unit = runBlocking {
        val methods = paymentMethodsInteractor.getPaymentMethods()

        Assert.assertEquals(2, methods.size)
        Assert.assertEquals(PaymentMethod("test_label_1", "http://test.link.logo/1"), methods[0])
        Assert.assertEquals(PaymentMethod("test_label_2", "http://test.link.logo/2"), methods[1])
    }
}