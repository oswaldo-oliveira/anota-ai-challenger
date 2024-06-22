package com.oswaldoliveira.desafioanotaai.domain.category.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("Category not found!");
    }
}
