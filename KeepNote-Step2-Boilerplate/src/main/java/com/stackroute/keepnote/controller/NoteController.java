package com.stackroute.keepnote.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.model.Note;

/*
 * Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@Controller
public class NoteController {
	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the persistence data. Each note
	 * should contain Note Id, title, content, status and created date. 
	 * 2. Add a new note which should contain the note id, title, content and status. 
	 * 3. Delete an existing note 
	 * 4. Update an existing note
	 * 
	 */

	/*
	 * Autowiring should be implemented for the NoteDAO.
	 * Create a Note object.
	 * 
	 */
	@Autowired
	 NoteDAO noteDao;
	/*
	 * Define a handler method to read the existing notes from the database and add
	 * it to the ModelMap which is an implementation of Map, used when building
	 * model data for use with views. it should map to the default URL i.e. "/index"
	 */

	/*
	 * Define a handler method which will read the NoteTitle, NoteContent,
	 * NoteStatus from request parameters and save the note in note table in
	 * database. Please note that the CreatedAt should always be auto populated with
	 * system time and should not be accepted from the user. Also, after saving the
	 * note, it should show the same along with existing messages. Hence, reading
	 * note has to be done here again and the retrieved notes object should be sent
	 * back to the view using ModelMap This handler method should map to the URL
	 * "/add".
	 */
	  @GetMapping("/")
	    public String readNotes(ModelMap model) {
	        model.addAttribute("note",noteDao.getAllNotes());
	        return "index";
	    }

	/*
	 * Define a handler method which will read the NoteId from request parameters
	 * and remove an existing note by calling the deleteNote() method of the
	 * NoteRepository class.This handler method should map to the URL "/delete".
	 */
	  @PostMapping("/add")
	   public String saveNote(@RequestParam("noteContent") String noteContent,@RequestParam("noteTitle") String noteTitle,@RequestParam("noteStatus") String noteStatus,ModelMap model) {
	       LocalDateTime createdAt = LocalDateTime.now();
	       Note note = new Note();
	       if(noteContent.isEmpty() || noteTitle.isEmpty()) {
	           
	           return "index";
	       }
	           else
	           {
	       note.setCreatedAt(createdAt);
	       note.setNoteStatus(noteStatus);
	       note.setNoteTitle(noteTitle);
	       note.setNoteContent(noteContent);
	       noteDao.saveNote(note);
	       model.addAttribute("list",noteDao.getAllNotes());
	       return "redirect:/";
	   }}

	/*
	 * Define a handler method which will update the existing note. This handler
	 * method should map to the URL "/update".
	 */
	  @RequestMapping("/delete")
	    public String deleteNote(@RequestParam("noteId") int noteId) {
	        noteDao.deleteNote(noteId);
	        return "redirect:/";
	        
	    } 
	  @PostMapping("/update")
	    public String updateNote(@RequestParam("noteId") int noteId,@RequestParam("noteContent") String noteContent,@RequestParam("noteTitle") String noteTitle,@RequestParam("noteStatus") String noteStatus,ModelMap model) {
	      Note note = new Note();
	      LocalDateTime createdAt = LocalDateTime.now();
	       note.setCreatedAt(createdAt);
	       note.setNoteId(noteId);
	       note.setNoteStatus(noteStatus);
	       note.setNoteTitle(noteTitle);
	       note.setNoteContent(noteContent);
		  noteDao.UpdateNote(note);
	        return "redirect:/";
	        
	    }
	  @GetMapping("/edit")
	  public String editNote(@RequestParam("noteId") int noteId,ModelMap model) {
		  model.addAttribute("editing",noteDao.getNoteById(noteId));
		  
		  return "edit";
	  }

}
