package com.patika.patikacohorthw2.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class Product {
    private String id;
    private String name;
    private  double price;
    private int stock;
}
