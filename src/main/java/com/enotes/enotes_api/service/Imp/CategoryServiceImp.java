package com.enotes.enotes_api.service.Imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.enotes.enotes_api.dto.CategoryDto;
import com.enotes.enotes_api.dto.CategoryResponse;
import com.enotes.enotes_api.entity.Category;
import com.enotes.enotes_api.repositary.CategoryRepositary;
import com.enotes.enotes_api.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepositary categoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {

        Category category = mapper.map(categoryDto, Category.class);

		if (ObjectUtils.isEmpty(category.getId())) {
			category.setIsDeleted(false);
			category.setCreatedBy(1);
			category.setCreatedOn(new Date());
		} else {
			updateCategory(category);
		}

		Category saveCategory = categoryRepo.save(category);
		if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		return true;

    }

    private void updateCategory(Category category) {
		Optional<Category> findById = categoryRepo.findById(category.getId());
		if (findById.isPresent()) {
			Category existCategory = findById.get();
			category.setCreatedBy(existCategory.getCreatedBy());
			category.setCreatedOn(existCategory.getCreatedOn());
			category.setIsDeleted(existCategory.getIsDeleted());
			
			category.setUpdatedBy(1);
			category.setUpdatedOn(new Date());
		}
	}

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findByIsDeletedFalse();

        List<CategoryDto> categoryDtoList = categories.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();

        return categoryDtoList;
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {

        List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
        List<CategoryResponse> categoryList = categories.stream().map(cat -> mapper.map(cat, CategoryResponse.class))
                .toList();
        return categoryList;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Optional<Category> findCategory = categoryRepo.findByIdAndIsDeletedFalse(id);
        if(findCategory.isPresent()){
            Category category = findCategory.get();
            return mapper.map(category,CategoryDto.class);

        }
        return null;
    }

    @Override
    public Boolean deleteCategorytById(Integer id) {
        Optional<Category> findCategory = categoryRepo.findById(id);
        if(findCategory.isPresent()){
            Category category = findCategory.get();
            category.setIsDeleted(true);
            categoryRepo.save(category);
            return true;

        }
        return false;
    }

}
