package com.enotes.enotes_api.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enotes.enotes_api.entity.FileDetails;

public interface FileRepository extends JpaRepository<FileDetails, Integer> {

}
