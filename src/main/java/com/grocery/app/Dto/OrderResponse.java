package com.grocery.app.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
public class OrderResponse {
    private Long userId;
    private List<OrderItemRequest> items;
}

