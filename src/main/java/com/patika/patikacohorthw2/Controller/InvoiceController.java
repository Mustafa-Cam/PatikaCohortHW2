package com.patika.patikacohorthw2.Controller;

import com.patika.patikacohorthw2.dto.Response.GenericResponse;
import com.patika.patikacohorthw2.dto.Response.InvoiceResponse;
import com.patika.patikacohorthw2.model.Invoice;
import com.patika.patikacohorthw2.model.SectorEnum;
import com.patika.patikacohorthw2.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;
//

    //
    @GetMapping
    public GenericResponse<List<InvoiceResponse>> getAll() {
        return GenericResponse.success(invoiceService.getAllInvoices());
    }

//    @GetMapping("Invoice/{id}")
//    public GenericResponse<Invoice> getInvoice(@PathVariable String id) {
//        return GenericResponse.success(invoiceService.getInvoice(id));
//    }
//
//    @GetMapping("/TotalAmountForCustomersWithName/{name}")
//    public GenericResponse<Double> getTotalAmountForCustomersWithName(@PathVariable String name) {
//        return GenericResponse.success(invoiceService.getTotalAmountForCustomersWithName(name));
//    }
//
    @GetMapping("/listInvoicesAboveAmount/{value}")
    public GenericResponse<List<InvoiceResponse>> listInvoicesAboveAmount(@PathVariable int  value) {
        return GenericResponse.success(invoiceService.getInvoicesAboveAmount(value));
    }
    @GetMapping("/getAverageOfInvoicesAboveAmount/{value}")
    public GenericResponse<Double> getAverageOfInvoicesAboveAmount(@PathVariable int  value) {
        return GenericResponse.success(invoiceService.getAverageOfInvoicesAboveAmount(value));
    }
    @GetMapping("/getCustomerNamesWithInvoicesBelowAmount/{value}")
    public GenericResponse<List<String>> getCustomerNamesWithInvoicesBelowAmount(@PathVariable int  value) {
        return GenericResponse.success(invoiceService.getCustomerNamesWithInvoicesBelowAmount(value));
    }
    @GetMapping("/getSectorsWithInvoicesInJuneBelowAverage/{value}")
    public GenericResponse<List<SectorEnum>> getSectorsWithInvoicesInJuneBelowAverage(@PathVariable int  value) {
        return GenericResponse.success(invoiceService.getSectorsWithInvoicesInJuneBelowAverage(value));
    }

}
