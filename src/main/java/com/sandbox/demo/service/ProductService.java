package com.sandbox.demo.service;

import com.sandbox.demo.model.Product;
import com.sandbox.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Page<Product> getProducts(String name, float price, int page, int size) {
        return productRepository.findAll(where(hasName(name)).or(hasPrice(price)), PageRequest.of(page, size, Sort.by("id")));
    }

    private static Specification<Product> hasName(String name) {
        return (product, cq, cb) -> cb.equal(product.get("name"), name);
    }

    private static Specification<Product> hasPrice(float price) {
        return (product, cq, cb) -> cb.equal(product.get("price"), price);
    }
}
