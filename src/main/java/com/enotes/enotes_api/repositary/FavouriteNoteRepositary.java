package com.enotes.enotes_api.repositary;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enotes.enotes_api.entity.FavouriteNote;

public interface FavouriteNoteRepositary extends JpaRepository<FavouriteNote,Integer> {

    List<FavouriteNote> findByUserId(int userId);
} 
