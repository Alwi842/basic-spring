package com.example.basic_spring.model;

import jakarta.persistence.*;

@Entity // ANOTASI untuk mapping tabel di database
@Table(name = "products") // tabel di database
public class Product {
    @Id // untuk nandain bahwa kolom ini adalah primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //untuk generate id increment otomatis
    @Column(name = "id") //buat ngambil kolom id di database
    private Long id;
    //nullable : nandiain apakah kolom tersebut boleh kosong atau tidak
    @Column(name="product_name", nullable = false)
    private String productName;

    @Column(name="product_price", nullable = false)
    private double productPrice;

    @Column(name="category")
    private String category;

    //getter buat ngambil data, setter buat ngubah/add data
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getProductName(){return productName;}
    public void setProductName(String productName){this.productName=productName;}

    public double getProductPrice(){return productPrice;}
    public void setProductPrice(double productPrice){this.productPrice=productPrice;}

    public String getCategory(){return category;}
    public void setCategory(String category){this.category=category;}

}
