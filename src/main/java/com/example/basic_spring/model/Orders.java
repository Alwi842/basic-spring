package com.example.basic_spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Orders {
    @Id // untuk nandain bahwa kolom ini adalah primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //untuk generate id increment otomatis
    @Column(name = "order_id", nullable = false) //buat ngambil kolom id di database
    private Long id;
    //nullable : nandiain apakah kolom tersebut boleh kosong atau tidak
    @Column(name="product_id", nullable = false)
    private Long productId;

    @Column(name="order_date", nullable = false)
    private String orderDate;

    @Column(name="quantity")
    private Long quantity;

    //getter buat ngambil data, setter buat ngubah/add data
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public Long getProductId(){return productId;}
    public void setProductId(Long productId){this.productId=productId;}

    public String getOrderDate(){return orderDate;}
    public void setOrderDate(String orderDate){this.orderDate=orderDate;}

    public Long getQuantity(){return quantity;}
    public void setQuantity(Long quantity){this.quantity=quantity;}
}
