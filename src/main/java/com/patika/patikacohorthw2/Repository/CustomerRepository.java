package com.patika.patikacohorthw2.Repository;


import com.patika.patikacohorthw2.model.Customer;
import com.patika.patikacohorthw2.model.Invoice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public  class CustomerRepository {
    private Set<Customer> customerDatabase = new HashSet<>();// key value ilişkisi

//    public  List<Order> getOrdersByCustomerId(String customerId) {
//        Customer customer = customerDatabase.get(customerId);
//        if (customer != null) {
//            return customer.getOrders();
//        }
//        return new ArrayList<>(); // Müşteri bulunamazsa boş liste döner
//    }

    public void add(Customer customer) {
        customerDatabase.add(customer);
    }

    public Customer getCustomer(String id) {
        return customerDatabase.stream().filter(c->c.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerDatabase.stream().toList());
    }

    public List<Customer> getCustomersContainingLetter(String letter ) {
        return getAllCustomers().stream()
                .filter(customer -> customer.getName().contains(letter))
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomersRegisteredInJune() {
        return getAllCustomers().stream()
                .filter(customer -> customer.getRegistrationDate().getMonthValue() == 6)
                .toList();

    }

    public double getTotalInvoiceAmountForCustomersRegisteredInJune() {
        List<Customer> customers = getAllCustomers().stream()
                .filter(customer -> customer.getRegistrationDate().getMonthValue() == 6)
                .toList();
        System.out.println("Total invoice amount for customers registered in june:"+customers);

        return customers.stream()
                .flatMap(customer -> customer.getInvoices().stream())
                .mapToDouble(Invoice::getAmount)
                .sum();
    }

}
