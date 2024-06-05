package com.patika.patikacohorthw2.Repository;


import com.patika.patikacohorthw2.model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    private Set<Order> orderDatabase = new HashSet<>();

    public void addOrder(Order order) {
        orderDatabase.add(order);
    }

    public Order getOrder(String id) {
//        System.out.println("getid \n"+orderDatabase.get(id));
        return orderDatabase.stream().filter(c->c.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderDatabase.stream().toList();
    }



}
