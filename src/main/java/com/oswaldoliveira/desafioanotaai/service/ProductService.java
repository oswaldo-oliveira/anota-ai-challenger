package com.oswaldoliveira.desafioanotaai.service;

import com.oswaldoliveira.desafioanotaai.domain.category.exception.CategoryNotFoundException;
import com.oswaldoliveira.desafioanotaai.domain.product.Product;
import com.oswaldoliveira.desafioanotaai.domain.product.ProductDTO;
import com.oswaldoliveira.desafioanotaai.domain.product.exception.ProductNotFoundException;
import com.oswaldoliveira.desafioanotaai.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;
    private final CategoryService categoryService;


    public Product createProduct(ProductDTO data) {
        var category = categoryService.findById(data.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        var product = new Product(data);
        product.setCategory(category);
        return productsRepository.save(product);
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Product updateProduct(ProductDTO data, String id) {
        var product = productsRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        if (!data.title().isEmpty()) product.setTitle(data.title());
        if (!data.description().isEmpty()) product.setDescription(data.description());
        if (data.price() != null) product.setPrice(data.price());
        categoryService.findById(data.categoryId()).ifPresent(product::setCategory);

        productsRepository.save(product);
        return product;
    }

    public void deleteProduct(String id) {
        var product = productsRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        productsRepository.delete(product);
    }
}
