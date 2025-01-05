package com.enotes.enotes_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enotes.enotes_api.dto.CategoryDto;
import com.enotes.enotes_api.dto.CategoryResponse;
import com.enotes.enotes_api.entity.Category;

@Service
public interface CategoryService {
    public Boolean saveCategory(CategoryDto catagoryDto);
    public List<CategoryDto> getAllCategory();
    public List<CategoryResponse> getActiveCategory();
    public CategoryDto getCategoryById(Integer id) throws Exception;
    public Boolean deleteCategorytById(Integer id);

}
