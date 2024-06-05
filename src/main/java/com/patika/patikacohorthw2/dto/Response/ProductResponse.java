package com.patika.patikacohorthw2.dto.Response;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private double price;
    private int stock;
}
