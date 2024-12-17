package com.grocery.app.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class OrderItemRequest {

    @NotNull(message = "product id is required")
    private int productId;

    @NotNull(message = "quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private int quantity;
}