package com.patika.patikacohorthw2.service;

import com.patika.patikacohorthw2.dto.Request.CustomerSaveRequest;
import com.patika.patikacohorthw2.dto.Response.CustomerResponse;
import com.patika.patikacohorthw2.Repository.CustomerRepository;
import com.patika.patikacohorthw2.converter.CustomerConverter;
import com.patika.patikacohorthw2.dto.Response.GenericResponse;
import com.patika.patikacohorthw2.exception.KitapYurdumException;
import com.patika.patikacohorthw2.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void addCustomer(CustomerSaveRequest customersaveRequest) {
        Customer customer = CustomerConverter.toCustomer(customersaveRequest);
        customerRepository.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
    public List<Customer> getAllCustomersWithInvoice(){
        return customerRepository.getAllCustomers();
    }

    public CustomerResponse getCustomer(String id) {
        System.out.println("customer geldi: "+customerRepository.getCustomer(id));
        return CustomerConverter.toResponse( customerRepository.getCustomer(id));
    }

    public Customer GetCustomer(String id){
        return customerRepository.getCustomer(id);
    }

    public List<CustomerResponse> getCustomersContainingLetter(String letter) {
        List<Customer> customer = customerRepository.getCustomersContainingLetter(letter);
        System.out.println("customer \n"+customer);

        if (customer.isEmpty()) {
            throw new KitapYurdumException("not found customer with letter " + letter);
        }
        return CustomerConverter.toResponse(customer);
    }

    public List<CustomerResponse> getCustomersRegisteredInJune() {
        return CustomerConverter.toResponse(customerRepository.getCustomersRegisteredInJune());
    }
    public List<Customer> getCustomersRegisteredInJuneNotRes() {
        return customerRepository.getCustomersRegisteredInJune();
    }

    public Double getTotalInvoiceAmountForCustomersRegisteredInJune() {
        return customerRepository.getTotalInvoiceAmountForCustomersRegisteredInJune();
    }
}