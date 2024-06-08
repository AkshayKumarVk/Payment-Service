package com.example.paymentservice.services.paymentGateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentGateway implements PaymentGateway {

   @Value("${Stripe.apiKey}")
   private String stripeApiSecret;

   @Override
   public String generatePaymentLink (String orderId, Long amount) throws StripeException {
	  Stripe.apiKey = stripeApiSecret;


	  PriceCreateParams priceParams =
			  PriceCreateParams.builder ()
					  .setCurrency ("inr")
					  .setUnitAmount (amount)
					  .setProductData (
							  PriceCreateParams.ProductData.builder ().setName (orderId).build ()
					  )
					  .build ();

	  Price price = Price.create (priceParams);

	  // Set your secret key. Remember to switch to your live secret key in production.
// See your keys here: https://dashboard.stripe.com/apikeys


	  String redirectUrl = "https://akshaykumarvk.me";

	  PaymentLinkCreateParams linkParams =
			  PaymentLinkCreateParams.builder ()
					  .addLineItem (
							  PaymentLinkCreateParams.LineItem.builder ()
									  .setPrice (price.getId ())
									  .setQuantity (1L)
									  .build ()
									  )
					  .setAfterCompletion (
							  PaymentLinkCreateParams.AfterCompletion.builder ()
									  .setType (PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
									  .setRedirect (
											  PaymentLinkCreateParams.AfterCompletion.Redirect.builder ()
													  .setUrl (redirectUrl)
													  .build ()
									  )
									  .build ()
					  )
					  .build ();

	  PaymentLink paymentLink = PaymentLink.create (linkParams);
	  System.out.println (paymentLink.getUrl ());
	  return paymentLink.getUrl ();
   }
}
