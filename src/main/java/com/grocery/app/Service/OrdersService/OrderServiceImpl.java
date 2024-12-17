package com.grocery.app.Service.OrdersService;

import com.grocery.app.Dto.OrderItemRequest;
import com.grocery.app.Dto.OrderRequest;
import com.grocery.app.Dto.OrderResponse;
import com.grocery.app.Exception.ResourceNotCreatedException;
import com.grocery.app.Exception.ResourceNotFoundException;
import com.grocery.app.Model.OrderItems;
import com.grocery.app.Model.Orders;
import com.grocery.app.Model.Products;
import com.grocery.app.Repository.OrderItemsRepository;
import com.grocery.app.Repository.OrdersRepository;
import com.grocery.app.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
/**
 * Service implementation for handling order-related operations.
 * This service handles the logic for creating an order, processing order items.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) throws Exception {

        try {
            // Create a new Order entity
            Orders order = new Orders();
            order.setUserId(orderRequest.getUserId());
            order.setOrderDate(LocalDateTime.now());
            ordersRepository.save(order);

            // Process each order item
            for (OrderItemRequest itemRequest : orderRequest.getItems()) {
                Products product = productRepository.findById(itemRequest.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + itemRequest.getProductId()));

                if (product.getStock_quantity() < itemRequest.getQuantity()) {
                    throw new ResourceNotFoundException("Not enough stock for product: " + product.getName());
                }

                // Create and save OrderItem
                OrderItems orderItem = new OrderItems();
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(itemRequest.getQuantity());
                orderItemsRepository.save(orderItem);
                return OrderResponse.builder()
                        .userId(orderRequest.getUserId())
                        .items(orderRequest.getItems())
                        .build();
            }
        } catch (Exception exception) {
            throw new ResourceNotCreatedException("Failed to create order: " + exception.getMessage());
        }
        return null;
    }
}
