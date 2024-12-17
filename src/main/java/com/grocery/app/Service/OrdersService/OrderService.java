package com.grocery.app.Service.OrdersService;

import com.grocery.app.Dto.OrderRequest;
import com.grocery.app.Dto.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orders) throws Exception;
}
