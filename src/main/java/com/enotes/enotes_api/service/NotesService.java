package com.enotes.enotes_api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.enotes.enotes_api.dto.NotesDto;
import com.enotes.enotes_api.entity.FileDetails;
import com.enotes.enotes_api.dto.NotesResponse;


public interface NotesService {

	public Boolean saveNotes(String notes,MultipartFile file) throws Exception;
	
	public List<NotesDto> getAllNotes();
	public NotesResponse getAllNotesByUser(Integer userId,Integer pageNo,Integer pageSize);
	public byte[] downloadFile(FileDetails fileDtls) throws Exception;

	public FileDetails getFileDetails(Integer id) throws Exception;

}
