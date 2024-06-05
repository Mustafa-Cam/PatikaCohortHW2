# Tüm müşterileri listeleme => CustomerService 28. satır, customercontroller 27. satır. 
public List<Customer> getAllCustomers() {
return customerRepository.getAllCustomers();
}

****************************
# yeni müşteri oluşturma => 
customerservice:
public void addCustomer(CustomerSaveRequest customersaveRequest) {
Customer customer = CustomerConverter.toCustomer(customersaveRequest);
customerRepository.add(customer);
}

customercontroller:
@PostMapping
public void addCustomer(@RequestBody CustomerSaveRequest request) {
customerService.addCustomer(request);
}

örnek post body:
{
"name": "Deniz",
"registrationDate": "2022-07-25",
"sector": "FINANCE"
}

ep:http://localhost:8080/api/v1/customers

**********************************


# içerisinde c harfi veya istediğin bir harf içeren müşteriler:

customerservice:
public List<CustomerResponse> getCustomersContainingLetter(String letter) {
List<Customer> customer = customerRepository.getCustomersContainingLetter(letter);
System.out.println("customer \n"+customer);
if (customer.isEmpty()) {
            throw new KitapYurdumException("not found customer with letter " + letter);
        }
        return CustomerConverter.toResponse(customer);
    }

customercontroller:
@GetMapping("/withLetter/{letter}")
public GenericResponse<List<CustomerResponse>> getCustomersContainingLetter(@PathVariable String letter) {
return  GenericResponse.success(customerService.getCustomersContainingLetter(letter));
}

örnek endpoint:http://localhost:8080/api/v1/customers/a

***************************************

# haziran ayında kayıt olmuş müşterinin toplam alışveriş tutarı:

customerservice:
public Double getTotalInvoiceAmountForCustomersRegisteredInJune() {
return customerRepository.getTotalInvoiceAmountForCustomersRegisteredInJune();
}
customercontroller:
@GetMapping("/getCustomersRegisteredInJuneWithInvoice")
public GenericResponse<Double> getTotalInvoiceAmountForCustomersRegisteredInJune() {
return  GenericResponse.success(customerService.getTotalInvoiceAmountForCustomersRegisteredInJune());
}

customerrepository:

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

ep: http://localhost:8080/api/v1/customers/getCustomersRegisteredInJuneWithInvoice

**********************************

# Tüm Faturaları listeleyen:
### invoiceRepository:
public List<Invoice> getAllInvoices() {
return new ArrayList<>(invoiceDatabase.stream().toList());
}

ep:http://localhost:8080/api/v1/invoice

**********************************

# 1500 veya istenilen miktarın üzerindeki faturaları listeleme:
### invoiceRepository:
public List<Invoice> getInvoicesAboveAmount(double amount) {
return invoiceDatabase.stream()
.filter(invoice -> invoice.getAmount() > amount)
.collect(Collectors.toList());
}

ep:http://localhost:8080/api/v1/invoice/listInvoicesAboveAmount/1500

**********************************

# sistemdeki 1500 veya üzeri faturaların ortlamasını hesaplayınız. 

## invoiceRepository:
public double getAverageOfInvoicesAboveAmount(double amount) {
return invoiceDatabase.stream()
.filter(invoice -> invoice.getAmount() > amount)
.mapToDouble(Invoice::getAmount)
.average()
.orElse(0);
}

ep: http://localhost:8080/api/v1/invoice/getAverageOfInvoicesAboveAmount/1500
**********************************


# sistemde 500 tl altındaki faturalara sahip müşterilerin isimlerini getir:
## invoiceRepository:
public List<String> getCustomerNamesWithInvoicesBelowAmount(int amount) {
return customerService.getAllCustomers().stream()
.filter(customer -> customer.getInvoices() != null)
.filter(customer -> customer.getInvoices().stream().anyMatch(invoice -> invoice.getAmount() < amount))
.map(Customer::getName)
.collect(Collectors.toList());
}

ep: http://localhost:8080/api/v1/invoice/getCustomerNamesWithInvoicesBelowAmount/500

**********************************

# haziran ayı faturalarının ortalaması 750 altı olan firmaların hangi sektörde olduğunu listeleyen:

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

ep: http://localhost:8080/api/v1/invoice/getSectorsWithInvoicesInJuneBelowAverage/750

**********************************

order oluşturmak için customerin yanı sıra product da aeklemek gerekli 

# product ekleme =>
### productRepository
public void addProduct(Product product) {
productDatabase.add(product);
}

örnek post body:
{
"name":"book1",
"price":"760",
"stock":"200"
}

ep:http://localhost:8080/api/v1/products

**********************************


# order oluşturma
### post body:
{
"customerID":"1",
"productID":["1"]
}

ep:http://localhost:8080/api/v1/orders

**********************************
