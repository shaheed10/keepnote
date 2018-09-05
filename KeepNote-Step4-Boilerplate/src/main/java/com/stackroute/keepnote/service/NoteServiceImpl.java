package com.stackroute.keepnote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.CategoryDAO;
import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.dao.ReminderDAO;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.Reminder;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn�t currently 
* provide any additional behavior over the @Component annotation, but it�s a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class NoteServiceImpl implements NoteService {

	/*
	 * Autowiring should be implemented for the NoteDAO,CategoryDAO,ReminderDAO.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	private NoteDAO noteDAO;
	private ReminderDAO reminderDAO;
	private CategoryDAO categoryDAO;
	@Autowired
	public NoteServiceImpl(NoteDAO noteDAO, ReminderDAO reminderDAO, CategoryDAO categoryDAO) {
		super();
		this.noteDAO = noteDAO;
		this.reminderDAO = reminderDAO;
		this.categoryDAO = categoryDAO;
	}


	/*
	 * This method should be used to save a new note.
	 */

	public boolean createNote(Note note) throws ReminderNotFoundException, CategoryNotFoundException {
		boolean b = noteDAO.createNote(note);
		if(note.getReminder()!=null && note.getCategory()!=null) {
			reminderDAO.getReminderById(note.getReminder().getReminderId());
			categoryDAO.getCategoryById(note.getCategory().getCategoryId());
		}else if(note.getReminder()!=null) {
			reminderDAO.getReminderById(note.getReminder().getReminderId());
			
		}else if(note.getCategory()!=null) {
			categoryDAO.getCategoryById(note.getCategory().getCategoryId());
			
		}
		return b;

	}

	
	/* This method should be used to delete an existing note. */

	public boolean deleteNote(int noteId) {
		try {
			if(noteDAO.deleteNote(noteId)) { 
				return true;
			}else {
				return false;}
			}


		 catch (NoteNotFoundException e) {
			
		 return false;
		}}
	/*
	 * This method should be used to get a note by userId.
	 */

	public List<Note> getAllNotesByUserId(String userId) {
		return noteDAO.getAllNotesByUserId(userId);

	}

	/*
	 * This method should be used to get a note by noteId.
	 */
	public Note getNoteById(int noteId) throws NoteNotFoundException {
		Note n = noteDAO.getNoteById(noteId);
		
			return n;
			
		

	}

	/*
	 * This method should be used to update a existing note.
	 */

	public Note updateNote(Note note, int id)
			throws ReminderNotFoundException, NoteNotFoundException, CategoryNotFoundException {
	if(note.getReminder()!=null && note.getCategory()!=null) {
		reminderDAO.getReminderById(note.getReminder().getReminderId());
		categoryDAO.getCategoryById(note.getCategory().getCategoryId());
	}else if(note.getReminder()!=null) {
		reminderDAO.getReminderById(note.getReminder().getReminderId());
		
	}else if(note.getCategory()!=null) {
		categoryDAO.getCategoryById(note.getCategory().getCategoryId());
		
	}
	noteDAO.getNoteById(id);
	noteDAO.UpdateNote(note);
	return note;

}}
