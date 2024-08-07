package com.example.OrderProcessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessing {

    Inventory inventory;
    PaymentProcessor paymentProcessor;

    @Autowired
    public OrderProcessing(Inventory inventory, PaymentProcessor paymentProcessor) {
        this.inventory = inventory;
        this.paymentProcessor = paymentProcessor;
    }

    public boolean processOrder(Order order)  {
        boolean orderProcessed = false;
        boolean sufficientQuantity;
        boolean sufficientPayment;

        sufficientQuantity = inventory.sufficientStock(order.getQuantity());
        sufficientPayment = paymentProcessor.processPayment(order.getTotal());

        if(sufficientPayment && sufficientQuantity) {
            inventory.reduceStock(order.getProduct(), order.getQuantity());
            orderProcessed = true;
        }
        return orderProcessed;
    }

}
