/*
 * 
 */
package com.semihbeceren.modamda.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.semihbeceren.modamda.model.User;
import com.semihbeceren.modamda.repository.UserRepository;

/**
 * @author semihbeceren
 *
 */
@Repository
public class JpaUserRepositoryImpl implements UserRepository{

	
	@PersistenceContext
    private EntityManager em;
	
	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.repository.UserRepository#findById(int)
	 */
	@Override
	public User findById(int id) throws DataAccessException {
		Query query = this.em.createQuery("SELECT user FROM User user left join fetch user.combines WHERE user.id =:id");
		query.setParameter("id", id);
		return (User)query.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.repository.UserRepository#save(com.semihbeceren.modamda.model.User)
	 */
	@Override
	public void save(User user) throws DataAccessException {
		if (user.getId() == null) {
            this.em.persist(user);
        } else {
            this.em.merge(user);
        }
	}

}
