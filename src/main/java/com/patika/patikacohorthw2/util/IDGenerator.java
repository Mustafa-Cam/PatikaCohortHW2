package com.patika.patikacohorthw2.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IDGenerator {
    private static long customerIdCounter = 1;
    private static long productIdCounter = 1;
    private static long orderIdCounter = 1;
    private static long invoiceIdCounter = 1;

    public static synchronized String generateCustomerId() {
        return String.valueOf(customerIdCounter++);
    }

    public static synchronized String generateProductId() {
        return String.valueOf(productIdCounter++);
    }

    public static synchronized String generateOrderId() {
        return String.valueOf(orderIdCounter++);
    }

    public static synchronized String generateInvoiceId() {
        return String.valueOf(invoiceIdCounter++);
    }
}
