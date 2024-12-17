package com.grocery.app.Controller.Orders;

import com.grocery.app.Dto.OrderRequest;
import com.grocery.app.Dto.OrderResponse;
import com.grocery.app.Model.Products;
import com.grocery.app.Service.OrdersService.OrderServiceImpl;
import com.grocery.app.Service.ProductService.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) throws Exception {
        var orderResponse = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Products>> getAllAvailableProducts(){
        var list = productService.getAllAvailableProducts();
        return ResponseEntity.ok(list);
    }
}
