package com.grocery.app.Model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "products")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "products_sequence", initialValue = 101, allocationSize = 1)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private long price;

    @Column
    private int stock_quantity;

    @Column
    private String status;

    @Column
    private String created_at;

    @Column
    private String updated_at;
}
