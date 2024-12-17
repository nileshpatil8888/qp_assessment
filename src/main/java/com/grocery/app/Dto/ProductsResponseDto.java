package com.grocery.app.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProductsResponseDto {

    private String name;


    private String description;


    private long price;


    private int stock_quantity;

    private String status;

    private String created_at;

    private String updated_at;
}
