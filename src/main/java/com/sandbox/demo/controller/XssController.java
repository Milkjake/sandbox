package com.sandbox.demo.controller;

import com.sandbox.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xss")
public class XssController {

    private ProductService productService;

    @Autowired
    public XssController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/test/{test}")
    public ResponseEntity getEverything(@PathVariable("test") String test) {

        return ResponseEntity.ok().build();
    }
}
