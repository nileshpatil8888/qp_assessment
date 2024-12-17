package com.grocery.app.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Orders order;

    @ManyToOne
    private Products product;

    private int quantity;
}
