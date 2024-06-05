package com.patika.patikacohorthw2.dto.Request;

import lombok.Data;

import java.util.List;

@Data
public class OrderSaveRequest {
    private String customerID;
    private List<String> productID;

}
