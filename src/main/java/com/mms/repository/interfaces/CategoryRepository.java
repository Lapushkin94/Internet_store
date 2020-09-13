package com.mms.repository.interfaces;

import com.mms.model.CategoryEntity;

import java.util.List;

public interface CategoryRepository {

    List<CategoryEntity> findAllCategories(int page);
    void saveCategory(CategoryEntity categoryEntity);
    void deleteCategory(CategoryEntity categoryEntity);
    void updateCategory(CategoryEntity categoryEntity);
    CategoryEntity findCategoryById(int id);
    int getCategoryCount();

}
