package com.patika.patikacohorthw2.dto.Response;

import com.patika.patikacohorthw2.dto.Request.CustomerSaveRequest;
import com.patika.patikacohorthw2.model.Customer;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String id;
    private CustomerResponse customer;
    private List<ProductResponse> products;
}
