package com.grocery.app.Controller.Product;

import com.grocery.app.Dto.ProductsRequestDto;
import com.grocery.app.Dto.ProductsResponseDto;
import com.grocery.app.Model.Products;
import com.grocery.app.Service.ProductService.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<ProductsResponseDto> add(@Valid @RequestBody ProductsRequestDto products){
        var productRes = productService.add(products);
        return ResponseEntity.ok(productRes);
    }

    @PutMapping
    public ResponseEntity<ProductsResponseDto> update(@Valid @RequestBody ProductsRequestDto products){
        var productsRes = productService.update(products);
        return ResponseEntity.ok(productsRes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable String id){
        productService.remove(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping()
    public List<Products> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Products getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }
}
