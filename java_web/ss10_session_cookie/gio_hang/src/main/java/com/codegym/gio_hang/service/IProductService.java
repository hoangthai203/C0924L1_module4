package com.codegym.gio_hang.service;

import com.codegym.gio_hang.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(Long id);
}
