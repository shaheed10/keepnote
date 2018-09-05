package com.stackroute.keepnote.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.User;

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
public class CategoryDAOImpl implements CategoryDAO {
	private SessionFactory sessionFactory;

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	@Autowired
	public CategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;

	}

	/*
	 * Create a new category
	 */
	public boolean createCategory(Category category) {
		sessionFactory.getCurrentSession().save(category);
		sessionFactory.getCurrentSession().flush();
		return true;

	}

	/*
	 * Remove an existing category
	 */
	public boolean deleteCategory(int categoryId) {
		Category c;
		try {
			c = getCategoryById(categoryId);
			if(c!=null)
			{
			sessionFactory.getCurrentSession().createQuery("Delete from Category where categoryId=:id").setParameter("id",categoryId).executeUpdate();
				
			return true;
		}	
		} catch (CategoryNotFoundException e) {
			
			e.printStackTrace();
		}return false;

	}
	/*
	 * Update an existing category
	 */

	public boolean updateCategory(Category category) {
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(category);
		sessionFactory.getCurrentSession().flush();
		return true;
		
	}
	/*
	 * Retrieve details of a specific category
	 */

	public Category getCategoryById(int categoryId) throws CategoryNotFoundException {
		 Category c=sessionFactory.getCurrentSession().get(Category.class, categoryId);
	        if(c!=null)
	        {
	            return c;
	        }
	        else
	        {
	            throw new CategoryNotFoundException("Note Not Found");
	        }
		

	}

	/*
	 * Retrieve details of all categories by userId
	 */
	public List<Category> getAllCategoryByUserId(String userId) {
		 Query<Category> query = sessionFactory.getCurrentSession().createQuery("From Category where categoryCreatedBy=:user").setParameter("user", userId);
         return query.getResultList();

	}

}
