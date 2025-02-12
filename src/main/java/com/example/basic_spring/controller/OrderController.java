package com.example.basic_spring.controller;

import com.example.basic_spring.model.Orders;
import com.example.basic_spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController //anotasi buat webservice yang handle parameter dan response http(get,post,put,delete)
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Orders> getAllProducts() throws SQLException {
        return orderService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getProductById(@PathVariable Long id) throws SQLException {
        Orders order = orderService.getOrderById(id);

        if (order!=null){
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping //add new data
    public ResponseEntity<Orders> addProduct(@RequestBody Orders orders) throws SQLException{
        orderService.addOrder(orders);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}") //update data
    public ResponseEntity<Orders> updateProduct(@PathVariable Long id,@RequestBody Orders orders) throws SQLException{
        orders.setId(id);
        orderService.updateOrder(orders);
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws SQLException{
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
