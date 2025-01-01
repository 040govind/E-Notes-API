package com.enotes.enotes_api.service.Imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.enotes.enotes_api.entity.Category;
import com.enotes.enotes_api.repositary.CategoryRepositary;
import com.enotes.enotes_api.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepositary categoryRepo;

    @Override
    public Boolean saveCategory(Category category) {
        category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
        Category SaveCategory = categoryRepo.save(category);
        
        if(ObjectUtils.isEmpty(SaveCategory)){
            return false;
        }
        return true;
        
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
		return categories;
    }

}
