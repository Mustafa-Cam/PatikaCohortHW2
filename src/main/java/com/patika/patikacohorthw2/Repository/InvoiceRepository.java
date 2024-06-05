package com.patika.patikacohorthw2.Repository;


import com.patika.patikacohorthw2.model.Invoice;
import com.patika.patikacohorthw2.model.Customer;
import com.patika.patikacohorthw2.model.SectorEnum;
import com.patika.patikacohorthw2.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class InvoiceRepository {
    private Set<Invoice> invoiceDatabase = new HashSet<>();

    private final CustomerService customerService;

    public void addInvoice(Invoice invoice) {
        invoiceDatabase.add(invoice);
        System.out.println("Invoice added: " + invoice);

    }


    public Invoice getInvoice(String id) {
        return invoiceDatabase.stream().filter(c->c.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoiceDatabase.stream().toList());
    }


    public List<Invoice> getInvoicesAboveAmount(double amount) {
        return invoiceDatabase.stream()
                .filter(invoice -> invoice.getAmount() > amount)
                .collect(Collectors.toList());
    }

    public double getAverageOfInvoicesAboveAmount(double amount) {
        return invoiceDatabase.stream()
                .filter(invoice -> invoice.getAmount() > amount)
                .mapToDouble(Invoice::getAmount)
                .average()
                .orElse(0);
    }

//    public List<String> getcustomernamesofinvoicesunderamount(int amount) {
//
//    }

    public List<String> getCustomerNamesWithInvoicesBelowAmount(int amount) {
        return customerService.getAllCustomers().stream()
                .filter(customer -> customer.getInvoices() != null)
                .filter(customer -> customer.getInvoices().stream().anyMatch(invoice -> invoice.getAmount() < amount))
                .map(Customer::getName)
                .collect(Collectors.toList());
    }

//
public List<SectorEnum> getSectorsWithInvoicesInJuneBelowAverage(double average) {
    return customerService.getAllCustomers().stream()
            .filter(customer -> customer.getInvoices() != null)
            .filter(customer -> {
                OptionalDouble averageAmount = customer.getInvoices().stream()
                        .filter(invoice -> invoice.getDate().getMonthValue() == 6)
                        .mapToDouble(Invoice::getAmount)
                        .average();
                return averageAmount.isPresent() && averageAmount.getAsDouble() < average;
            })
            .map(Customer::getSector)
            .distinct()
            .collect(Collectors.toList());
}


}
