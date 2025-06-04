package com.codegym.quan_ly_san_pham.service;

import com.codegym.quan_ly_san_pham.Repository.IProductRepository;
import com.codegym.quan_ly_san_pham.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        if (product.getId() == 0) {
            productRepository.save(product); // Thêm mới
        } else {
            productRepository.update(product); // Cập nhật
        }
    }

    @Override
    public void update(int id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct != null) {
            updatedProduct.setId(id);
            productRepository.update(updatedProduct);
        }
    }

    @Override
    public void remove(int id) {
        productRepository.remove(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.searchByName(name);
    }
}