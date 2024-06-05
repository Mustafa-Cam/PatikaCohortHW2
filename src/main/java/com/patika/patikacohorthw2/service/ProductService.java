package com.patika.patikacohorthw2.service;

import com.patika.patikacohorthw2.Repository.ProductRepository;
import com.patika.patikacohorthw2.converter.ProductConverter;
import com.patika.patikacohorthw2.dto.Request.ProductSaveRequest;
import com.patika.patikacohorthw2.dto.Response.ProductResponse;
import com.patika.patikacohorthw2.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public void createProduct(ProductSaveRequest productSaveRequest) { // list de yapabiliriz.
        Product product = ProductConverter.toProduct(productSaveRequest);
        productRepository.addProduct(product);
    }

    public Product getProduct(String id) {
        return productRepository.getProduct(id);
    }

    public List<ProductResponse> getAll() {
        List<Product> product =  productRepository.getProducts();
        return ProductConverter.toResponse(product);

    }

    public List<ProductResponse> getAllById(List<String> id) {
        List<Product> products = productRepository.getProductByID(id);
        return ProductConverter.toResponse(products);
    }
}
