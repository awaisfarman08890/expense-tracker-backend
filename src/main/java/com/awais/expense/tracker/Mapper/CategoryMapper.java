package com.awais.expense.tracker.Mapper;

import com.awais.expense.tracker.Entity.Category;
import com.awais.expense.tracker.dto.CategoryDto;



public class CategoryMapper {
    public static Category maptocategorydto(CategoryDto categoryDto) {
        return new Category(
          categoryDto.id(),
          categoryDto.name()
        );
    }
    public static CategoryDto maptocategory(Category category){
        return new CategoryDto(
                category.getId(),
                category.getName()
                );
    }
}
