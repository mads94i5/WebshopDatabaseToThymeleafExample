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
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "test");

            PreparedStatement psts = conn.prepareStatement("SELECT * FROM product");

            ResultSet rs = psts.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                products.add(new Product(id, name, price));
            }
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            e.printStackTrace();
        }
        return products;
    }

    public void create(Product newProduct) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "test");

            String query = "INSERT INTO product (name, price) VALUES (?, ?)";
            PreparedStatement psts = conn.prepareStatement(query);

            psts.setString(1, newProduct.getName());
            psts.setInt(2, newProduct.getPrice());

            psts.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            e.printStackTrace();
        }
    }

    public Product findById(int id) {
        Product foundProduct = new Product();
        foundProduct.setId(id);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "test");

            String query = "SELECT * FROM product WHERE id=?";
            PreparedStatement psts = conn.prepareStatement(query);

            psts.setInt(1, id);

            ResultSet rs = psts.executeQuery();

            rs.next();
            String name = rs.getString(2);
            int price = rs.getInt(3);

            foundProduct.setName(name);
            foundProduct.setPrice(price);

        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            e.printStackTrace();
        }
        return foundProduct;
    }



    public void update(Product product) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "test");

            String query = "UPDATE product " +
                    "SET name=?, price=? WHERE id=?";
            PreparedStatement psts = conn.prepareStatement(query);

            psts.setString(1, product.getName());
            psts.setInt(2, product.getPrice());
            psts.setInt(3, product.getId());

            psts.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "test");

            String query = "DELETE FROM product WHERE id=?";
            PreparedStatement psts = conn.prepareStatement(query);

            psts.setInt(1, id);

            psts.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            e.printStackTrace();
        }
    }
}
