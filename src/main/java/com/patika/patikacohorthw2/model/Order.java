package com.patika.patikacohorthw2.model;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class Order {
    private String id;
    private String customerId;
    private List<String> productID;
}