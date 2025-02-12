package com.example.basic_spring.controller;

import com.example.basic_spring.model.Product;
import com.example.basic_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

//controller buat routing

@RestController //anotasi buat webservice yang handle parameter dan response http(get,post,put,delete)
@RequestMapping("/api/products") //anotasi buat nentuin endpoint dari controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping //anotasi untuk requests get
    public List<Product> getAllProducts() throws SQLException {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")//get data by id
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws SQLException {
        Product product = productService.getProductById(id);
        //error handling
        if (product!=null){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping //add new data
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws SQLException{
        productService.addProduct(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}") //update data
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product) throws SQLException{
        product.setId(id);
        productService.updateProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws SQLException{
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
