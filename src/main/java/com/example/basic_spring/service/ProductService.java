package com.example.basic_spring.service;

import com.example.basic_spring.model.Product;
import com.example.basic_spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service //Anotasi springboot untuk nyediain logika bisnis seperti CRUD (manipulasi data)
public class ProductService {
    @Autowired //Untuk inject/menghubungi service ke repository
    private ProductRepository productRepository;

    //logic buat get all data
    public List<Product> getAllProducts() throws SQLException {
        return productRepository.findAll();
    }
    //get product by id
    public Product getProductById(Long id) throws SQLException{
        return productRepository.findById(id);
    }
    //delete product by id
    public void deleteProductById(Long id) throws SQLException{
        productRepository.deleteById(id);
    }
    //add product
    public void addProduct(Product product) throws SQLException{
        productRepository.save(product);
    }
    //update product
    public void updateProduct(Product product) throws SQLException{
        productRepository.update(product);
    }
}
