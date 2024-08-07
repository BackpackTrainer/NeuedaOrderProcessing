package com.example.OrderProcessing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderProcessingUnitTests {
    @Autowired
    OrderProcessing uut;

    @MockBean
    Inventory mockInventory;
    @MockBean
    PaymentProcessor mockPaymentProcessor;

    Product testProduct = new Product("Super Widget", 10);
    Order testOrder = new Order(testProduct, 2);

    @ParameterizedTest
    @CsvSource({"true, true, true, 1", "true, false, false, 0", "false, true, false, 0", "false, false, false, 0"})
    public void testOrderProcessing(boolean sufficientQuantity, boolean sufficientPayment, boolean expectedResult, int timesCalled)  {
        when(mockInventory.sufficientStock(testOrder.getQuantity())).thenReturn(sufficientQuantity);
        when(mockPaymentProcessor.processPayment(testOrder.getTotal())).thenReturn(sufficientPayment);

        boolean actualResult = uut.processOrder(testOrder);

        assertEquals(expectedResult, actualResult);
        verify(mockInventory, times(timesCalled)).reduceStock(testOrder.getProduct(), testOrder.getQuantity());
    }
}
