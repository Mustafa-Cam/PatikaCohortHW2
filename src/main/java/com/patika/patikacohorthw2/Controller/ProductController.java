package com.patika.patikacohorthw2.Controller;


import com.patika.patikacohorthw2.dto.Request.ProductSaveRequest;
import com.patika.patikacohorthw2.dto.Response.GenericResponse;
import com.patika.patikacohorthw2.dto.Response.ProductResponse;
import com.patika.patikacohorthw2.model.Product;
import com.patika.patikacohorthw2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProductSaveRequest request) {
        productService.createProduct(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public GenericResponse<List<ProductResponse>> getAll() {
        return GenericResponse.success(productService.getAll());
    }

    @GetMapping("/getProduct/{id}")
    public GenericResponse<Product> getProduct(@PathVariable String id){
        return GenericResponse.success(productService.getProduct(id));
    }
}

