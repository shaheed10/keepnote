package com.stackroute.keepnote.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import com.stackroute.keepnote.model.Note;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */

@Repository
@Transactional
public class NoteDAOImpl implements NoteDAO {
	@Autowired
	 private SessionFactory sessionFactory;

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	@Autowired
	public NoteDAOImpl(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(Note note) {
		sessionFactory.getCurrentSession().save(note);
		sessionFactory.getCurrentSession().flush();
		return true;

	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Note WHERE noteId="+noteId).executeUpdate();
		return true;
	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	
	public List<Note> getAllNotes() {
		String hql = "FROM Note ORDER BY createdAt DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.getResultList(); 
	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {
		
		return (Note)sessionFactory.getCurrentSession().get(Note.class,noteId);

	}

	/* Update existing note */

	public boolean UpdateNote(Note note) {
		sessionFactory.getCurrentSession().update(note);
		sessionFactory.getCurrentSession().flush();
		
		return true;

	}

}
