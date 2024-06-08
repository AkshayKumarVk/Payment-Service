package com.example.paymentservice.services.paymentGateways;

import com.stripe.exception.StripeException;

public interface PaymentGateway {

   String generatePaymentLink (String orderId, Long amount) throws StripeException;
}
