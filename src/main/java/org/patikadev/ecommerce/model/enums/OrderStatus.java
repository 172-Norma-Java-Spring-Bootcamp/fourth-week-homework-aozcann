package org.patikadev.ecommerce.model.enums;

// Order has some status, it is using on order operation.
public enum OrderStatus {
    INIT, // Initial status of creation,
    ORDER_TAKEN, // Payment success status
    ORDER_CONFIRMED, // Seller approved the order
    PREPARING, // Seller preparing the order
    ON_WAY, // Seller gave the order to shipping
    DELIVERED, // Customer take the order
    CANCELED, // Order canceled
    RETURNED // Customer returned the order
}
