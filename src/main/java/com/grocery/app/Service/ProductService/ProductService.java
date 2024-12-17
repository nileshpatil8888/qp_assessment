package com.grocery.app.Service.ProductService;

import com.grocery.app.Dto.ProductsRequestDto;
import com.grocery.app.Dto.ProductsResponseDto;
import com.grocery.app.Model.Products;

import java.util.List;

public interface ProductService {

    ProductsResponseDto add(ProductsRequestDto products);
    ProductsResponseDto update(ProductsRequestDto products);
    void remove(String id);
    List<Products> getAllProducts();
    Products getProduct(String id);
    List<Products> getAllAvailableProducts();
}
