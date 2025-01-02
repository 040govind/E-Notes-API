package com.enotes.enotes_api.repositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enotes.enotes_api.entity.Category;

@Repository
public interface CategoryRepositary extends JpaRepository<Category,Integer>{

    List<Category> findByIsActiveTrueAndIsDeletedFalse();
    Optional<Category> findByIdAndIsDeletedFalse(Integer id);
    List<Category> findByIsDeletedFalse();

}
