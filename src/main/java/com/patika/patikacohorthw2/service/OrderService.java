package com.patika.patikacohorthw2.service;

import com.patika.patikacohorthw2.Repository.OrderRepository;
import com.patika.patikacohorthw2.converter.CustomerConverter;
import com.patika.patikacohorthw2.converter.OrderConverter;
import com.patika.patikacohorthw2.dto.Request.OrderSaveRequest;
import com.patika.patikacohorthw2.dto.Response.OrderResponse;
import com.patika.patikacohorthw2.exception.KitapYurdumException;
import com.patika.patikacohorthw2.model.Customer;
import com.patika.patikacohorthw2.model.Invoice;
import com.patika.patikacohorthw2.model.Order;
import com.patika.patikacohorthw2.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final InvoiceService invoiceService;
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final CustomerService customerService;
    private final ProductService productService;

    public synchronized void createOrder(OrderSaveRequest orderSaveRequest) {

        if(customerService.GetCustomer(orderSaveRequest.getCustomerID())==null){
            throw new KitapYurdumException("Customer not found");
        }
//        if(productService.getProduct(orderSaveRequest.getProductID())==null){
//            throw new KitapYurdumException("Customer not found");
//        }

        Order order = orderConverter.toOrder(orderSaveRequest);
        System.out.println( "order \n"+order);
        orderRepository.addOrder(order);
        Invoice invoice =  invoiceService.createInvoice(order);
        System.out.println( "invoice \n"+invoice);
//        List<Invoice> invoices = new ArrayList<>();
//        invoices.add(invoice);
//        Customer cu =  CustomerConverter.toCustomer(customerService.getCustomer(orderSaveRequest.getCustomerID()),invoices);
        Customer customer =  customerService.GetCustomer(orderSaveRequest.getCustomerID());
        customer.addInvoice(invoice);

        System.out.println("getinvoice \n"+customer.getInvoices());
        System.out.println("customer \n"+customer);

//        order.addInvoiceToOrder(invoiceService.getInvoice(order.getId()));
//         addProductToOrder(order,orderSaveRequest.getProducts());
}




    public List<OrderResponse> getAllOrders() {
        List<Order> allOrders = orderRepository.getAllOrders();
        System.out.println("allorders"+allOrders);
        return orderConverter.toResponse(allOrders);


//        for (Order order : allOrders) {
//            System.out.println("Order ID: " + order.getId());
//            System.out.println("Customer Id: " + order.getCustomerId());
//            System.out.println("Order products:"+ order.getProductID());
//
//        }

    }

    public Order getOrder(String id) {
        return orderRepository.getOrder(id);
    }

    public Integer getProductCountForCustomer(String id) {
        List<Order> allOrders = orderRepository.getAllOrders();
        int productCount = 0;
        for (Order order : allOrders) {
            if (order.getCustomerId().equals(id)) {
                productCount += order.getProductID().size();
            }
        }
        return productCount;
    }


}
