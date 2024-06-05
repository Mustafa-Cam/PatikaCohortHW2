package com.patika.patikacohorthw2.service;

import com.patika.patikacohorthw2.Repository.CustomerRepository;
import com.patika.patikacohorthw2.dto.Response.InvoiceResponse;
import com.patika.patikacohorthw2.dto.Response.ProductResponse;
import com.patika.patikacohorthw2.Repository.InvoiceRepository;
import com.patika.patikacohorthw2.Repository.ProductRepository;
import com.patika.patikacohorthw2.model.Customer;

import com.patika.patikacohorthw2.converter.InvoiceConverter;
import com.patika.patikacohorthw2.model.Invoice;
import com.patika.patikacohorthw2.model.Order;
import com.patika.patikacohorthw2.model.SectorEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ProductService productService;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public Invoice createInvoice(Order order) {
        double totalAmount = productService.getAllById(order.getProductID()).stream().mapToDouble(ProductResponse::getPrice).sum();
        Invoice invoice =  InvoiceConverter.toInvoice(order,totalAmount);
        System.out.println(invoice);
        invoiceRepository.addInvoice(invoice);
        return invoice;

//        order.setInvoice(invoice);
    }


    public List<InvoiceResponse> getAllInvoices() {
        return InvoiceConverter.toResponse(invoiceRepository.getAllInvoices());
    }

    public List<InvoiceResponse> getInvoicesAboveAmount(double amount) {
        return InvoiceConverter.toResponse( invoiceRepository.getInvoicesAboveAmount(amount));
    }

    public double   getAverageOfInvoicesAboveAmount(double amount) {
        return  invoiceRepository.getAverageOfInvoicesAboveAmount(amount);
    }

    public List<String> getCustomerNamesWithInvoicesBelowAmount(int amount) {
        return invoiceRepository.getCustomerNamesWithInvoicesBelowAmount(amount);
    }
    public List<SectorEnum> getSectorsWithInvoicesInJuneBelowAverage(int amount) {
        return invoiceRepository.getSectorsWithInvoicesInJuneBelowAverage(amount);
    }



//    public List<SectorEnum> getSectorsWithInvoicesInJuneBelowAverage(double average) {
//        return customerService.getAllCustomers().stream()
//                .filter(customer -> customer.getInvoices().stream()
//                        .filter(invoice -> invoice.getDate().getMonthValue()==6)
//                        .mapToDouble(Invoice::getAmount)
//                        .average()
//                        .orElse(0) < average)
//                .map(Customer::getSector)
//                .distinct()
//                .collect(Collectors.toList());
//    }
}
