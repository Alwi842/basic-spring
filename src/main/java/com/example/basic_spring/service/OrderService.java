package com.example.basic_spring.service;

import com.example.basic_spring.model.Orders;
import com.example.basic_spring.model.Product;
import com.example.basic_spring.repository.OrderRepository;
import com.example.basic_spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    //logic buat get all data
    public List<Orders> getAllProducts() throws SQLException {
        return orderRepository.findAll();
    }
    //get product by id
    public Orders getOrderById(Long id) throws SQLException{
        return orderRepository.findById(id);
    }
    //delete product by id
    public void deleteOrderById(Long id) throws SQLException{
        orderRepository.deleteById(id);
    }
    //add product
    public void addOrder(Orders orders) throws SQLException{
        orderRepository.save(orders);
    }
    //update product
    public void updateOrder(Orders orders) throws SQLException{
        orderRepository.update(orders);
    }
}
