package org.kodigo.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.kodigo.domain.Category;
import org.kodigo.repository.ICategoryRepository;

import java.util.List;

@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.getCategoryById(categoryId);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.getCategoryByName(categoryName);
    }

    @Override
    public void createCategory(Category category) {
        val categories = categoryRepository.getAll();
        categories.stream().filter(c -> c.getName().equals(category.getName()))
                .findFirst()
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Category already exists");
                });
        categoryRepository.createCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        val categories = categoryRepository.getAll();

        val searchCategory = categories.stream()
                .filter(p -> p.getId().equals(category.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        boolean isRemoved = categories.remove(searchCategory);

        if (!isRemoved) {
            throw new IllegalArgumentException("Category not found");
        } else {
            categoryRepository.updateCategory(category);
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        val categories = categoryRepository.getAll();

        val category = categories.stream().filter(c -> c.getId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        categoryRepository.deleteCategory(category.getId());
    }
}
