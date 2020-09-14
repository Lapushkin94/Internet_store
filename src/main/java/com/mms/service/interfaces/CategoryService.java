package com.mms.service.interfaces;

import com.mms.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories(int page);
    void addCategory(CategoryDTO categoryDTO);
    void deleteCategory(CategoryDTO categoryDTO);
    void editCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategory(int id);
    int getCategoryCount();


}