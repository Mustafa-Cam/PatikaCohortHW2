package com.patika.patikacohorthw2.model;


import lombok.*;

import java.time.LocalDate;

//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class Invoice {
    private final String id;
    private double amount;
    private LocalDate date;
    private Order order;
}

