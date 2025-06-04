package com.codegym.quan_ly_san_pham.Repository;

import com.codegym.quan_ly_san_pham.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
    void update(Product product);
    void remove(int id);
    List<Product> searchByName(String name);
}