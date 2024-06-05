package com.patika.patikacohorthw2.converter;


import com.patika.patikacohorthw2.dto.Response.InvoiceResponse;
import com.patika.patikacohorthw2.model.Invoice;
import com.patika.patikacohorthw2.model.Order;
import com.patika.patikacohorthw2.util.IDGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // nesne oluşturulmasın diye.

public class InvoiceConverter {

    public static Invoice toInvoice(Order order, double totalAmount ) {
        return Invoice.builder()
                .id(IDGenerator.generateInvoiceId())
                .order(order)
                .date(LocalDate.of(2023,6,23))
                .amount(totalAmount)
                .build();
    }

    public static InvoiceResponse toResponse(Invoice invoice) {
        return InvoiceResponse.builder()
                .id(invoice.getId())
                .totalAmount(invoice.getAmount())
                .order(invoice.getOrder())
                .build();
    }

    public static List<InvoiceResponse> toResponse(List<Invoice> invoice) {
        return invoice
                .stream()
                .map(InvoiceConverter::toResponse)
                .collect(Collectors.toList());
    }
}
