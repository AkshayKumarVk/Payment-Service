package com.example.paymentservice.services.paymentGateways;

import org.springframework.stereotype.Service;

@Service("razorPay")
public class RazorPayPaymentGateway implements PaymentGateway {

   @Override
   public String generatePaymentLink (String orderId, Long amount) {
	  return "";
   }
}
