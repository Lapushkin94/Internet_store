package com.mms.service.interfaces;

import com.mms.dto.CategoryDTO;
import com.mms.dto.ProductDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories(int page);

    List<CategoryDTO> getAllCategoriesWithoutPages();

    void addCategory(CategoryDTO categoryDTO);

    void deleteCategory(CategoryDTO categoryDTO);

    void editCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategory(int id);

    int getCategoryCount();

    CategoryDTO getCategoryByName(String nameOfCategory);

    void changeCategoriesForProductList(List<ProductDTO> productDTOS);

    String addCategoryAndReturnResult(CategoryDTO categoryDTO);

    String checkCategoryNameAndReturnStatus(String newNameOfCategory);

}
