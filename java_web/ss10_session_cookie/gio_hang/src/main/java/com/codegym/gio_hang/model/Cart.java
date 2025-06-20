package com.codegym.gio_hang.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items = new LinkedHashMap<>();

    public void add(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public void update(Long productId, int quantity) {
        for (Product p : items.keySet()) {
            if (p.getId().equals(productId)) {
                items.put(p, quantity);
                return;
            }
        }
    }

    public void remove(Long productId) {
        items.entrySet().removeIf(entry -> entry.getKey().getId().equals(productId));
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getTotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
