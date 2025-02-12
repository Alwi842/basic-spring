package com.example.basic_spring.repository;

import com.example.basic_spring.model.Orders;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository
public class OrderRepository {
    private final DataSource dataSource;
    public OrderRepository(DataSource dataSource){this.dataSource = dataSource;}

    public List<Orders> findAll() throws SQLException {
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from orders")){
            List<Orders> orderList = new ArrayList<>();
            while (rs.next()){
                orderList.add(mapToOrder(rs));
            }
            return orderList;
        }
    }
    //method untuk mapping data dari tabel
    private Orders mapToOrder(ResultSet rs) throws SQLException{
        Orders orders = new Orders();
        orders.setId(rs.getLong("order_id")); //atur nilai id
        orders.setProductId(rs.getLong("product_id")); //atur nilai productName
        orders.setOrderDate(rs.getString("order_date")); //atur nilai productPrice
        orders.setQuantity(rs.getLong("quantity")); // atur nilai cagetoryId
        return orders;
    }

    //ngambilDataPakeId
    public Orders findById(Long id) throws SQLException{
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from orders where order_id = ?");){

            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return mapToOrder(rs);
            }
            return null;
        }
    }
    public void save(Orders orders) throws SQLException {
        String query = "insert into orders (product_id, order_date,quantity) values (?,?,?)";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, orders.getProductId());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now().toString()));
            preparedStatement.setLong(3, orders.getQuantity()); //atur category
            preparedStatement.executeUpdate(); //save data ke objek produk
        }
    }
    //update data
    public void update(Orders orders) throws SQLException {
        String query = "update orders set product_id = ?, quantity = ?, order_date = ? where order_id=?";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, orders.getProductId());
            preparedStatement.setLong(2, orders.getQuantity()); //atur category
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now().toString()));
            preparedStatement.setLong(4, orders.getId());
            preparedStatement.executeUpdate(); //save data ke objek produk
        }
    }
    //delete data
    public void deleteById(Long id) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from orders where order_id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
