package com.enotes.enotes_api.schdular;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.enotes.enotes_api.entity.Notes;
import com.enotes.enotes_api.repositary.NotesRepository;
@Component
public class NotesSchedular {

	@Autowired
	private NotesRepository notesRepo;

	@Scheduled(cron = "0 0 0 * * ?")
   //@Scheduled(cron = "* * * ? * *")
	public void deleteNotesSchdular() {
		// 20-nov -14 nov -7days
        System.out.println("*******************************************HHHHHHHHHHIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		LocalDateTime cutOffDate = LocalDateTime.now().minusDays(7);
		List<Notes> deleteNotes = notesRepo.findAllByIsDeletedAndDeletedOnBefore(true, cutOffDate);
		notesRepo.deleteAll(deleteNotes);
	}

}
