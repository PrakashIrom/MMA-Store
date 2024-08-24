package com.example.paypal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.paypal.android.corepayments.CoreConfig
import com.paypal.android.corepayments.PayPalSDKError
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutClient
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutListener
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutRequest
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutResult

/*
singleTop or singleTask Modes: In these modes, if an instance of the activity is already at the top of the stack, onNewIntent
is called instead of creating a new instance.
Deep Link Handling: When PayPal redirects to your app using a deep link, the new intent carries the payment information.
onNewIntent allows you to handle this without restarting the activity.
Data Processing: You can use the onNewIntent method to process the new intent data and continue the user flow seamlessly.
*/

class PaypalService : FragmentActivity() {

    private lateinit var config: CoreConfig

    val returnUrl = "my-paypal-scheme://return"

    lateinit var payPalWebCheckoutClient: PayPalWebCheckoutClient

    fun payPalWebCheckoutTapped(payPalWebCheckoutRequest: PayPalWebCheckoutRequest) {
        payPalWebCheckoutClient.start(payPalWebCheckoutRequest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        config = CoreConfig(

            //Authentication: It acts as a credential that allows your app to communicate securely with PayPal’s servers.
            //Scopes and Permissions: Through your clientId, PayPal knows which services and APIs your app is authorized to use.
            // It also associates the transactions and operations performed with your PayPal account.
            clientId = "AbZcp1B-oG_q2qpxMoFJeoa5GC5m8Z7qIG4rmQmHLpqaNfmOAApdhAJy_lDByv1ec5IAAx4-TYXZpwFm",
            environment = com.paypal.android.corepayments.Environment.SANDBOX, // or Environment.LIVE for production
        )

        payPalWebCheckoutClient = PayPalWebCheckoutClient(
            this, //The activity provides context to the SDK, allowing it to access resources,
            // display UI elements (like the web view for checkout), and interact with the Android system
            config,
            returnUrl,
        )

        payPalWebCheckoutClient.listener = object : PayPalWebCheckoutListener {
            override fun onPayPalWebSuccess(result: PayPalWebCheckoutResult)
            {
                // order was approved and is ready to be captured/authorized (see step 7)
            }
            override fun onPayPalWebFailure(error: PayPalSDKError) {
                // handle the error
            }
            override fun onPayPalWebCanceled() {
                // the user canceled the flow
            }
        }
    }

    //When your activity is launched with singleTop or singleTask launch modes, and a new intent for that activity arrives,
    // onNewIntent() is called instead of creating a new instance of the activity.
    //When PayPal redirects the user back to your app after the web checkout, it uses a deep link to launch your activity
    // (or bring it to the foreground). The onNewIntent() function is where you would handle this deep link, extract the payment
    // result from the newIntent, and proceed accordingly (e.g., show a success message, update the order status, etc.).

    override fun onNewIntent(newIntent: Intent)
    {
        super.onNewIntent(newIntent)
        intent = newIntent
    }

}

//singleTop or singleTask Modes: In these modes, if an instance of the activity is already at the top of the stack,
// onNewIntent is called instead of creating a new instance.
//Deep Link Handling: When PayPal redirects to your app using a deep link, the new intent carries the payment information.
// onNewIntent allows you to handle this without restarting the activity.
//Data Processing: You can use the onNewIntent method to process the new intent data and continue the user flow seamlessly.



