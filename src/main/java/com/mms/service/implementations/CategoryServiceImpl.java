package com.mms.service.implementations;

import com.mms.dto.CategoryDTO;
import com.mms.dto.converter.CategoryConverter;
import com.mms.repository.interfaces.CategoryRepository;
import com.mms.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.mms.dto.converter.CategoryConverter.toDto;
import static com.mms.dto.converter.CategoryConverter.toEntity;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

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
    public void addCategory(CategoryDTO categoryDTO) {
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

}
