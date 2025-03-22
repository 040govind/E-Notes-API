package com.enotes.enotes_api.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enotes.enotes_api.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {
   public List<Todo> findByCreatedBy(Integer userId);
}
