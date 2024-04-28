package org.kodigo.repository;

import org.kodigo.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements ICategoryRepository {

    private final ArrayList<Category> categories = new ArrayList<>(mockCategories());

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categories.stream()
                .filter(c -> c.getId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categories.stream()
                .filter(c -> c.getName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Override
    public void createCategory(Category category) {
        categories.add(category);
    }

    @Override
    public void updateCategory(Category category) {
        categories.add(category);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        categories.removeIf(p -> p.getId().equals(categoryId));
    }

    private List<Category> mockCategories() {
        return List.of(
                Category.builder()
                        .name("Electronics")
                        .description("Electronic products")
                        .build(),
                Category.builder()
                        .name("Clothing")
                        .description("Clothing products")
                        .build(),
                Category.builder()
                        .name("Books")
                        .description("Books products")
                        .build(),
                Category.builder()
                        .name("Home")
                        .description("Home products")
                        .build()
        );
    }
}
