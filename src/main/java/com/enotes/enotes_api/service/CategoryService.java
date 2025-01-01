package com.enotes.enotes_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enotes.enotes_api.entity.Category;

@Service
public interface CategoryService {
    public Boolean saveCategory(Category catagory);
    public List<Category> getAllCategory();
}
