package com.enotes.enotes_api.repositary;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enotes.enotes_api.entity.Notes;


public interface NotesRepository extends JpaRepository<Notes, Integer>{
    Page<Notes> findByCreatedBy(Integer userId,Pageable pageable);
    List<Notes> findByCreatedByAndIsDeletedTrue(Integer userId);
    Page<Notes> findByCreatedByAndIsDeletedFalse(Integer userId, Pageable pageable);
    List<Notes> findAllByIsDeletedAndDeletedOnBefore(boolean b, LocalDateTime cutOffDate);
}
