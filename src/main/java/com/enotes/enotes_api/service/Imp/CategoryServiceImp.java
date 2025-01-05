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
import com.enotes.enotes_api.exception.ExistDataException;
import com.enotes.enotes_api.exception.ResourceNotFoundException;
import com.enotes.enotes_api.repositary.CategoryRepositary;
import com.enotes.enotes_api.service.CategoryService;
import com.enotes.enotes_api.util.Validation;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepositary categoryRepo;

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
	private Validation validation;

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {

        // Validation Checking
		validation.categoryValidation(categoryDto);
        // check category exist or not
		Boolean exist = categoryRepo.existsByName(categoryDto.getName().trim());
		if (exist) {
			// throw error
			throw new ExistDataException("Category already exist");
		}
        Category category = mapper.map(categoryDto, Category.class);

		if (ObjectUtils.isEmpty(category.getId())) {
			category.setIsDeleted(false);
			//category.setCreatedBy(1);
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
			
			//category.setUpdatedBy(1);
			//category.setUpdatedOn(new Date());
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
    public CategoryDto getCategoryById(Integer id) throws Exception {
        Category category = categoryRepo.findByIdAndIsDeletedFalse(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id=" + id));

		if (!ObjectUtils.isEmpty(category)) {
			category.getName().toUpperCase();
			return mapper.map(category, CategoryDto.class);
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
