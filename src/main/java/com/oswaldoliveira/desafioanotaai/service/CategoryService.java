package com.oswaldoliveira.desafioanotaai.service;

import com.oswaldoliveira.desafioanotaai.domain.category.Category;
import com.oswaldoliveira.desafioanotaai.domain.category.CategoryDTO;
import com.oswaldoliveira.desafioanotaai.domain.category.exception.CategoryNotFoundException;
import com.oswaldoliveira.desafioanotaai.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryDTO data) {
        var category = new Category(data);
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(CategoryDTO data, String id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        if (!data.title().isEmpty()) category.setTitle(data.title());
        if (!data.description().isEmpty()) category.setDescription(data.description());
        categoryRepository.save(category);
        return category;
    }

    public void deleteCategory(String id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);
    }
}
