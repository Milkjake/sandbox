package com.sandbox.demo.controller;

import com.sandbox.demo.model.Product;
import com.sandbox.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/spec")
    public Page<Product> getProducts(
            @RequestParam("name") String name,
            @RequestParam(value = "price") Float price,
            @RequestParam(value = "bought", required = false) Timestamp bought,
            @RequestParam(value = "sold", required = false) Timestamp sold,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            Pageable pageable) {

        return productService.getProducts(name, price, page, size);
    }

//    @GetMapping("/spec")
//    public Page<Product> getProducts(
//            @And({
//                    @Spec(path = "name", spec = Equal.class),
//                    @Spec(path = "price", spec = Equal.class),
//                    @Spec(path = "startDate", spec = GreaterThan.class),
//                    @Spec(path = "endDate", spec = LessThan.class)
//            }) Specification<Product> productSpec,
//            Pageable pageable) {
//
//        return productService.getProducts(productSpec, pageable);
//    }
}
