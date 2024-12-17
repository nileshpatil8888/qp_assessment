package com.grocery.app.Service.ProductService;

import com.grocery.app.Dto.ProductsRequestDto;
import com.grocery.app.Dto.ProductsResponseDto;
import com.grocery.app.Exception.CustomNullPointerException;
import com.grocery.app.Exception.ResourceNotCreatedException;
import com.grocery.app.Exception.ResourceNotFoundException;
import com.grocery.app.Model.Products;
import com.grocery.app.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
/** This service helps admin to Add product, get product, update product details related operations. */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductsResponseDto add(ProductsRequestDto products) {
        try {
            Timestamp createdAt = Timestamp.from(Instant.now());
            Products product = Products.builder()
                    .name(products.getName())
                    .description(products.getDescription())
                    .price(products.getPrice())
                    .stock_quantity(products.getStock_quantity())
                    .status(products.getStatus())
                    .created_at(String.valueOf(createdAt))
                    .build();
            productRepository.save(product);
            return createResponseDto(products, product);
        } catch (Exception exception) {
            throw new ResourceNotCreatedException("Failed to create exception: " + exception.getMessage());
        }
    }

    private static ProductsResponseDto createResponseDto(ProductsRequestDto products, Products product) {
        return ProductsResponseDto.builder()
                .name(products.getName())
                .description(products.getDescription())
                .price(products.getPrice())
                .status(products.getStatus())
                .created_at(product.getCreated_at())
                .stock_quantity(products.getStock_quantity())
                .build();
    }

    @Override
    public ProductsResponseDto update(ProductsRequestDto products) {
        try {
            Products product = productRepository.findById(Integer.valueOf(products.getId())).get();
            if(product == null){
                throw new ResourceNotFoundException("Update failed: The product with the given details does not exist: " + product);
            }
            updateProduct(products, product);
            return updateProductsResponseDto(products, product);
        } catch (Exception exception) {
            throw new ResourceNotCreatedException("Failed to update :"+ exception.getMessage());
        }
    }

    private void updateProduct(ProductsRequestDto products, Products product) {
        Timestamp createdAt = Timestamp.from(Instant.now());
        product.setName(products.getName());
        product.setDescription(products.getDescription());
        product.setStatus(products.getStatus());
        product.setUpdated_at(String.valueOf(createdAt));
        product.setPrice(products.getPrice());
        product.setStock_quantity(products.getStock_quantity());
        productRepository.save(product);
    }

    private static ProductsResponseDto updateProductsResponseDto(ProductsRequestDto products, Products product) {
        return ProductsResponseDto.builder()
                .name(products.getName())
                .description(products.getDescription())
                .price(products.getPrice())
                .status(products.getStatus())
                .updated_at(product.getUpdated_at())
                .stock_quantity(products.getStock_quantity())
                .build();
    }

    @Override
    public void remove(String id) {
        try {
            productRepository.deleteById(Integer.valueOf(id));
        } catch (Exception exception){
            throw new ResourceNotFoundException("Failed to delete product :" + exception.getMessage());
        }
    }

    @Override
    public List<Products> getAllProducts() {
        try {
            List<Products> products = productRepository.findAll();
            return products;
        } catch (Exception exception){
            throw new ResourceNotFoundException("No products available into inventory :" + exception.getMessage());
        }
    }

    @Override
    public Products getProduct(String id) {
        try {
            return productRepository.findById(Integer.valueOf(id)).get();
        } catch (Exception exception){
            throw new ResourceNotFoundException("No product found for provided details :" + exception.getMessage());
        }
    }

    @Override
    public List<Products> getAllAvailableProducts() {
        try {
            List<Products> products = productRepository.findAll();
            List<Products> productsList = new ArrayList<>();
            for (Products product : products) {
                if (product.getStock_quantity() > 0) {
                    productsList.add(product);
                }
            }
            return productsList;
        } catch (Exception exception) {
            throw new ResourceNotFoundException("Stock Not available:" + exception.getMessage());
        }
    }
}
