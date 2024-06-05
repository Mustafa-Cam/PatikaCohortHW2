package com.patika.patikacohorthw2.Repository;


import com.patika.patikacohorthw2.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private Set< Product> productDatabase = new HashSet<>();

    public void addProduct(Product product) {
        productDatabase.add(product);
    }

    public Product getProduct(String id) {
        return productDatabase.stream().filter(c->c.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Product> getProducts(){
        return new ArrayList<>(productDatabase.stream().toList());
    }
    public List<Product> getProductByID(List<String> ids){
        List<Product> products = new ArrayList<>();
        for (String id : ids) {
            Product product = getProduct(id);
            if (product != null) {
                products.add(product);
            }
        }
        return products;
    }
}
