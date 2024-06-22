package com.oswaldoliveira.desafioanotaai.controller;

import com.oswaldoliveira.desafioanotaai.domain.product.Product;
import com.oswaldoliveira.desafioanotaai.domain.product.ProductDTO;
import com.oswaldoliveira.desafioanotaai.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createCategory(@RequestBody ProductDTO data) {
        return ResponseEntity.ok().body(productService.createProduct(data));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateCategory(@RequestBody ProductDTO data, @PathVariable String id) {
        return ResponseEntity.ok().body(productService.updateProduct(data, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
