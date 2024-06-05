package com.patika.patikacohorthw2.converter;

import com.patika.patikacohorthw2.dto.Request.ProductSaveRequest;
import com.patika.patikacohorthw2.dto.Response.ProductResponse;
import com.patika.patikacohorthw2.model.Product;
import com.patika.patikacohorthw2.util.IDGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {

    public static Product toProduct(ProductSaveRequest request) {

        return Product.builder()
                .id(IDGenerator.generateProductId())
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
    }
    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .price(product.getPrice())
                .build();
    }
    public static List<ProductResponse> toResponse(List<Product> products) {
        return products
                .stream()
                .map(ProductConverter::toProductResponse)
                .collect(Collectors.toList());
    }
}
