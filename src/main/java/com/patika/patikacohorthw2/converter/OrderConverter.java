package com.patika.patikacohorthw2.converter;

import com.patika.patikacohorthw2.dto.Request.OrderSaveRequest;
import com.patika.patikacohorthw2.dto.Response.OrderResponse;
import com.patika.patikacohorthw2.model.Order;
import com.patika.patikacohorthw2.service.CustomerService;
import com.patika.patikacohorthw2.service.InvoiceService;
import com.patika.patikacohorthw2.service.ProductService;
import com.patika.patikacohorthw2.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {

    private final CustomerService customerService;

    private final ProductService productService;

    private final InvoiceService invoiceService;

    @Autowired
    public OrderConverter(CustomerService customerService, ProductService productService, InvoiceService invoiceService) {
        this.customerService = customerService;
        this.productService = productService;
        this.invoiceService= invoiceService;
    }


    public  Order toOrder(OrderSaveRequest request) {

        return Order.builder()
                .id(IDGenerator.generateOrderId())
                .productID(request.getProductID())
                .customerId(request.getCustomerID())
                .build();

    }

    public   OrderResponse toResponse(Order order) {
            System.out.println("orderid: "+order.getId());
        return OrderResponse.builder()
                .id(order.getId())
                .products(productService.getAllById(order.getProductID()))
                .customer(customerService.getCustomer(order.getCustomerId()))
                .build();
    }

    public List<OrderResponse> toResponse(List<Order> orderList) {
        return orderList
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
