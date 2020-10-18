package com.mms.service.implementations;

import com.mms.dto.CategoryDTO;
import com.mms.dto.ProductDTO;
import com.mms.dto.converterDTO.CategoryConverter;
import com.mms.dto.converterDTO.ProductConverter;
import com.mms.model.CategoryEntity;
import com.mms.repository.interfaces.CategoryRepository;
import com.mms.repository.interfaces.ProductRepository;
import com.mms.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.CategoryConverter.toDto;
import static com.mms.dto.converterDTO.CategoryConverter.toEntity;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class.getName());

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public List<CategoryDTO> getAllCategories(int page) {
        return categoryRepository.findAllCategories(page).stream()
                .map(CategoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CategoryDTO> getAllCategoriesWithoutPages() {
        return categoryRepository.findAllCategoriesWithoutPages().stream()
                .map(CategoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addCategory(CategoryDTO categoryDTO) {
        logger.info("adding category");
        categoryRepository.saveCategory(toEntity(categoryDTO));
    }

    @Override
    @Transactional
    public void deleteCategory(CategoryDTO categoryDTO) {
        categoryRepository.deleteCategory(toEntity(categoryDTO));
    }

    @Override
    @Transactional
    public void editCategory(CategoryDTO categoryDTO) {
        logger.info("editing category");
        categoryRepository.updateCategory(toEntity(categoryDTO));
    }

    @Override
    @Transactional
    public CategoryDTO getCategory(int id) {
        return toDto(categoryRepository.findCategoryById(id));
    }

    @Override
    @Transactional
    public int getCategoryCount() {
        return categoryRepository.getCategoryCount();
    }

    @Override
    @Transactional
    public CategoryDTO getCategoryByName(String nameOfCategory) {
        return CategoryConverter.toDto(categoryRepository.findCategoryByName(nameOfCategory));
    }

    @Override
    @Transactional
    public void changeCategoriesForProductList(List<ProductDTO> productDTOS) {

        logger.info("changing categories to delete");
        CategoryEntity newCategoryDTO = categoryRepository.findCategoryById(1);
        for (ProductDTO prod : productDTOS) {
            prod.setCategory(newCategoryDTO);
            productRepository.updateProduct(ProductConverter.toEntity(prod));
        }

    }

}
