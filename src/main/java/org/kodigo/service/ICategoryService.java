package org.kodigo.service;

import org.kodigo.domain.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAll();

    Category getCategoryById(Integer categoryId);

    Category getCategoryByName(String categoryName);

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Integer categoryId);
}
