package com.awais.expense.tracker.Service.imp;

import com.awais.expense.tracker.Entity.Category;
import com.awais.expense.tracker.Mapper.CategoryMapper;
import com.awais.expense.tracker.Service.CategoryService;
import com.awais.expense.tracker.dto.CategoryDto;
import com.awais.expense.tracker.exceptions.ResourceNotFoundException;
import com.awais.expense.tracker.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
       Category category = CategoryMapper.maptocategorydto(categoryDto);
       Category savedCategory = categoryRepository.save(category);
       return CategoryMapper.maptocategory(category);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
        return CategoryMapper.maptocategory(category);

    }

    @Override
    public List<CategoryDto> getAllCategories() {
     List<Category> categories = categoryRepository.findAll();
     return categories.stream().map((CategoryMapper::maptocategory)).toList();
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
        category.setName(categoryDto.name());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.maptocategory(updatedCategory);

    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
        categoryRepository.delete(category);
    }
}
