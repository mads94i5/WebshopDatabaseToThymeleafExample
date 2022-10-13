package com.example.webshopdat22v2.repository;
import com.example.webshopdat22v2.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop","root","test");
            PreparedStatement psts = conn.prepareStatement("SELECT * FROM product");
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                products.add(new Product(id, name, price));
                System.out.println("id: " + id + " - name: " + name + " - price: " + price);
            }
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            e.printStackTrace();
        }
        return products;
    }
}
