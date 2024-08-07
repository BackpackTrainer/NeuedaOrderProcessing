package com.example.OrderProcessing;

import org.springframework.stereotype.Component;

@Component
public interface Inventory {
    boolean sufficientStock(int quantity);

    void reduceStock(Product product, int quantity);
}
