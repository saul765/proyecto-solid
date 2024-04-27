package org.kodigo.repository;

import org.kodigo.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements ICategoryRepository {

    private final ArrayList<Category> categories = new ArrayList<>(mockCaterogies());

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
        categories.stream().filter(c -> c.getName().equals(category.getName()))
                .findFirst()
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Category already exists");
                });
        categories.add(category);
    }

    @Override
    public void updateCategory(Category category) {

        Category searchCategory = categories.stream()
                .filter(p -> p.getId().equals(category.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        boolean isRemoved = categories.remove(searchCategory);

        if (!isRemoved) {
            throw new IllegalArgumentException("Product not found");
        } else {
            categories.add(category);
        }

    }

    @Override
    public void deleteCategory(Integer categoryId) {

        boolean isRemoved = categories.removeIf(p -> p.getId().equals(categoryId));

        if (!isRemoved) {
            throw new IllegalArgumentException("Category not found");
        }
    }

    private List<Category> mockCaterogies() {
        return List.of(
                Category.builder().name("Electronics").build(),
                Category.builder().name("Clothing").build(),
                Category.builder().name("Books").build(),
                Category.builder().name("Home").build()
        );
    }
}
