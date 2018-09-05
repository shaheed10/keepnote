package com.stackroute.keepnote.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Reminder;

/*
 * This class is implementing the UserDAO interface. This class has to be annotated with 
 * @Repository annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, 
 * thus clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */
@Repository
@Transactional
public class ReminderDAOImpl implements ReminderDAO {
	private SessionFactory sessionFactory;
	
	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	@Autowired
	public ReminderDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;

	}

	/*
	 * Create a new reminder
	 */

	public boolean createReminder(Reminder reminder) {
		sessionFactory.getCurrentSession().save(reminder);
		sessionFactory.getCurrentSession().flush();
		return true;

	}
	
	/*
	 * Update an existing reminder
	 */

	public boolean updateReminder(Reminder reminder) {
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(reminder);
		sessionFactory.getCurrentSession().flush();
		return true;

	}

	/*
	 * Remove an existing reminder
	 */
	
	public boolean deleteReminder(int reminderId) {
		Reminder r;
		try {
			r = getReminderById(reminderId);
			if(r!=null)
			{
			sessionFactory.getCurrentSession().createQuery("Delete from Reminder where reminderId=:id").setParameter("id",reminderId).executeUpdate();
				
			return true;
		}	
		} catch (ReminderNotFoundException e) {
			
			e.printStackTrace();
		}return false;

	}

	/*
	 * Retrieve details of a specific reminder
	 */
	
	public Reminder getReminderById(int reminderId) throws ReminderNotFoundException {
		Reminder r=sessionFactory.getCurrentSession().get(Reminder.class, reminderId);
        if(r!=null)
        {
            return r;
        }
        else
        {
            throw new ReminderNotFoundException("Note Not Found");
        }
	

	}

	/*
	 * Retrieve details of all reminders by userId
	 */
	
	public List<Reminder> getAllReminderByUserId(String userId) {
		 Query<Reminder> query = sessionFactory.getCurrentSession().createQuery("From Reminder where reminderCreatedBy=:user").setParameter("user", userId);
         return query.getResultList();

	}

}
