package com.patika.patikacohorthw2.model;


import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Customer {
    private  String id;
    private String name;
    private LocalDate registrationDate;
    private SectorEnum sector;
    private List<Invoice> invoices;

    public Customer(String id, String name, LocalDate registrationDate, SectorEnum sector) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
        this.sector = sector;
    }

    public void addInvoice(Invoice invoice){
        if(this.invoices == null) {
            this.invoices = new ArrayList<>();
        }
        this.invoices.add(invoice);
    }

}

