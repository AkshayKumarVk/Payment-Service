package com.example.paymentservice.services.paymentGateways;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;

public interface PaymentGateway {

   public String  generatePaymentLink(String orderId, Long amount) throws StripeException;
}
