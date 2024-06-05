package com.patika.patikacohorthw2.converter;


import com.patika.patikacohorthw2.dto.Request.CustomerSaveRequest;
import com.patika.patikacohorthw2.dto.Response.CustomerResponse;
import com.patika.patikacohorthw2.model.Customer;
import com.patika.patikacohorthw2.model.Invoice;
import com.patika.patikacohorthw2.service.CustomerService;
import com.patika.patikacohorthw2.util.IDGenerator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomerConverter {

   private static  CustomerService customerService;

    public static Customer toCustomer(CustomerResponse customerResponse, List<Invoice> invoice) {
        return Customer.builder()
                .name(customerResponse.getName())
                .registrationDate(customerResponse.getRegistrationDate())
                .sector(customerResponse.getSector())
                .invoices(invoice)
                .build();
    }

    public static Customer toCustomer(CustomerSaveRequest customerSaveRequest) {
        return Customer.builder()
                .id(IDGenerator.generateCustomerId())
                .name(customerSaveRequest.getName())
                .registrationDate(customerSaveRequest.getRegistrationDate())
                .sector(customerSaveRequest.getSector())
                .build();
    }


    public static CustomerResponse toResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .registrationDate(customer.getRegistrationDate())
                .sector(customer.getSector())
                .build();
    }


    public static List<CustomerResponse> toResponse(List<Customer> customerList){
       return customerList.stream()
                .map(CustomerConverter::toResponse)
                .collect(Collectors.toList());
    }
}
