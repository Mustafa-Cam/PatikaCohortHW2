package com.patika.patikacohorthw2.Controller;

import com.patika.patikacohorthw2.dto.Request.OrderSaveRequest;
import com.patika.patikacohorthw2.dto.Response.GenericResponse;
import com.patika.patikacohorthw2.dto.Response.OrderResponse;
import com.patika.patikacohorthw2.model.Order;
import com.patika.patikacohorthw2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderSaveRequest request) {
        orderService.createOrder(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getOrders")
    public GenericResponse<List<OrderResponse>> getOrders(){
        return GenericResponse.success(orderService.getAllOrders());
    }

    @GetMapping("/getOrder/{id}")
    public GenericResponse<Order> getOrder(@PathVariable String id){
        return GenericResponse.success(orderService.getOrder(id));
    }

    @GetMapping("/getProductCount/{customerId}")
    public GenericResponse<Integer> getProductCount(@PathVariable String customerId){
        return GenericResponse.success(orderService.getProductCountForCustomer(customerId));
    }

}
