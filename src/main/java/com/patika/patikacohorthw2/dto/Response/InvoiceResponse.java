package com.patika.patikacohorthw2.dto.Response;

import com.patika.patikacohorthw2.model.Order;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceResponse {
    private String id;
    private Order order;
    private double totalAmount;
}
