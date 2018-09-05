package com.stackroute.keepnote.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */
@Entity
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NoteId;
	private String NoteTitle,NoteContent,NoteStatus;
	private LocalDateTime createdAt;

	public Note() {

	}

	

	public Note(int noteId, String noteTitle, String noteContent, String noteStatus, LocalDateTime createdAt) {
		super();
		NoteId = noteId;
		NoteTitle = noteTitle;
		NoteContent = noteContent;
		NoteStatus = noteStatus;
		this.createdAt = createdAt;
	}



	public int getNoteId() {
		return NoteId;
	}



	public void setNoteId(int noteId) {
		NoteId = noteId;
	}



	public String getNoteTitle() {
		return NoteTitle;
	}



	public void setNoteTitle(String noteTitle) {
		NoteTitle = noteTitle;
	}



	public String getNoteContent() {
		return NoteContent;
	}



	public void setNoteContent(String noteContent) {
		NoteContent = noteContent;
	}



	public String getNoteStatus() {
		return NoteStatus;
	}



	public void setNoteStatus(String noteStatus) {
		NoteStatus = noteStatus;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	

	

}
