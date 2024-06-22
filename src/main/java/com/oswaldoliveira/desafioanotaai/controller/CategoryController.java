package com.oswaldoliveira.desafioanotaai.controller;

import com.oswaldoliveira.desafioanotaai.domain.category.Category;
import com.oswaldoliveira.desafioanotaai.domain.category.CategoryDTO;
import com.oswaldoliveira.desafioanotaai.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO data) {
        return ResponseEntity.ok().body(categoryService.createCategory(data));
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryDTO data, @PathVariable String id) {
        return ResponseEntity.ok().body(categoryService.updateCategory(data, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
