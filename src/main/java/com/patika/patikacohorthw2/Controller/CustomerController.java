package com.patika.patikacohorthw2.Controller;

import com.patika.patikacohorthw2.dto.Request.CustomerSaveRequest;
import com.patika.patikacohorthw2.dto.Response.CustomerResponse;
import com.patika.patikacohorthw2.dto.Response.GenericResponse;
import com.patika.patikacohorthw2.model.Customer;
import com.patika.patikacohorthw2.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {


    private final CustomerService customerService;


    @PostMapping
    public void addCustomer(@RequestBody CustomerSaveRequest request) {
        customerService.addCustomer(request);
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/withLetter/{letter}")
    public GenericResponse<List<CustomerResponse>> getCustomersContainingLetter(@PathVariable String letter) {
        return  GenericResponse.success(customerService.getCustomersContainingLetter(letter));
    }

@GetMapping("/getCustomersRegisteredInJune")
    public GenericResponse<List<CustomerResponse>> getCustomersRegisteredInJune() {
        return  GenericResponse.success(customerService.getCustomersRegisteredInJune());
    }

@GetMapping("/getCustomersRegisteredInJuneWithInvoice")
    public GenericResponse<Double> getTotalInvoiceAmountForCustomersRegisteredInJune() {
        return  GenericResponse.success(customerService.getTotalInvoiceAmountForCustomersRegisteredInJune());
    }
}