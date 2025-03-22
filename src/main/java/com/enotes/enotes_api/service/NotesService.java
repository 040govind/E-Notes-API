package com.enotes.enotes_api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.enotes.enotes_api.dto.FavouriteNoteDto;
import com.enotes.enotes_api.dto.NotesDto;
import com.enotes.enotes_api.entity.FileDetails;
import com.enotes.enotes_api.dto.NotesResponse;


public interface NotesService {

	public Boolean saveNotes(String notes,MultipartFile file) throws Exception;
	
	public List<NotesDto> getAllNotes();
	public NotesResponse getAllNotesByUser(Integer userId,Integer pageNo,Integer pageSize);
	public byte[] downloadFile(FileDetails fileDtls) throws Exception;

	public FileDetails getFileDetails(Integer id) throws Exception;

	public void softDeleteNotes(Integer id) throws Exception;
	public void restoreNotes(Integer id) throws Exception;
	public List<NotesDto> getUserRecycleBinNotes(Integer userId);
	public void hardDeleteNotes(Integer id) throws Exception;

	public void emptyRecycleBin(int userId);
	public void favoriteNotes(Integer noteId) throws Exception;

	public void unFavoriteNotes(Integer noteId) throws Exception;

	public List<FavouriteNoteDto> getUserFavoriteNotes() throws Exception;
	public Boolean copyNotes(Integer id) throws Exception;

}
