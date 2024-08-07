package com.example.OrderProcessing;

import org.springframework.stereotype.Component;

@Component public interface PaymentProcessor {
    boolean processPayment(int amount);
}
