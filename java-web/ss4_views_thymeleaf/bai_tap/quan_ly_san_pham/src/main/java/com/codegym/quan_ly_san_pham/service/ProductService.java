package com.codegym.quan_ly_san_pham.service;

import com.codegym.quan_ly_san_pham.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "iPhone", 1000, "Apple phone", "Apple"));
        products.add(new Product(2, "Galaxy", 900, "Samsung phone", "Samsung"));
        products.add(new Product(3, "Xiaomi Mi", 400, "Xiaomi phone", "Xiaomi"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public void save(Product product) {
        // Gán id tự động nếu là thêm mới
        int maxId = products.stream().mapToInt(Product::getId).max().orElse(0);
        product.setId(maxId + 1);
        products.add(product);
    }

    @Override
    public Product findById(int id) {
        Optional<Product> result = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        return result.orElse(null);
    }

    @Override
    public void update(int id, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, updatedProduct);
                return;
            }
        }
    }

    @Override
    public void remove(int id) {
        products.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Product> searchByName(String name) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}

