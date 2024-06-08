package com.example.paymentservice.services;

import com.example.paymentservice.services.paymentGateways.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

   private final PaymentGateway paymentGateway;

   PaymentService (@Qualifier("stripe") PaymentGateway paymentGateway) {
	  this.paymentGateway = paymentGateway;
   }

   public String generatePaymentLink (String orderId, Long amount) throws StripeException {
	  return paymentGateway.generatePaymentLink (orderId, amount);
   }
}
