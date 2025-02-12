package com.example.basic_spring.repository;

import com.example.basic_spring.model.Product;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//KONEKSI KE DATABASE MENGGUNAKAN JDBC(TRADISIONAL)
@Repository //anotasi buat nandain kalo ini repository
public class ProductRepository {
    private final DataSource dataSource;
    public ProductRepository(DataSource dataSource){this.dataSource = dataSource;} //constructor

    //repo untuk get all data product
    //List<Product> : simpen objek dari model produk kedalam array list
    //throw SQLException : untuk ngembaliin error jika operasi gagal.
    public List<Product> findAll() throws SQLException{
        try(Connection connection = dataSource.getConnection(); //buat koneksi ke database
            Statement statement = connection.createStatement();//createStatement buat manggil fungsi query
        ResultSet rs = statement.executeQuery("select * from products")){
             List<Product> productList = new ArrayList<>();//array kosong buat nampung hasil
             //looping data yang kesimpendari hasil query
             while (rs.next()){
                 productList.add(mapToProduct(rs));//productList[] + mapToProduct(rs) {} = [{...}]
             }
             return productList;
        }
    }
    //method untuk mapping data dari tabel
    private Product mapToProduct(ResultSet rs) throws SQLException{
        Product product = new Product();
        product.setId(rs.getLong("id")); //atur nilai id
        product.setProductName(rs.getString("product_name")); //atur nilai productName
        product.setProductPrice(rs.getDouble("product_price")); //atur nilai productPrice
        product.setCategory(rs.getString("category")); // atur nilai cagetoryId
        return product;
    }

    //ngambilDataPakeId
    public Product findById(Long id) throws SQLException{
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from products where id = ?");){

            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()){
                    return mapToProduct(rs);
                }
                return null;
            }
    }
    public void save(Product product) throws SQLException {
        String query = "insert into products (product_name, product_price,category) values (?,?,?)";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.setString(3, product.getCategory()); //atur category
            preparedStatement.executeUpdate(); //save data ke objek produk
        }
    }
    //update data
    public void update(Product product) throws SQLException {
        String query = "update products set product_name = ?, product_price = ?, category = ? where id=?";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.setString(3, product.getCategory()); //atur category
            preparedStatement.setLong(4, product.getId());
            preparedStatement.executeUpdate(); //save data ke objek produk
        }
    }
    //delete data
    public void deleteById(Long id) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from products where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
