/*
 * 
 */
package com.semihbeceren.modamda.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.semihbeceren.modamda.model.Combine;
import com.semihbeceren.modamda.repository.CombineRepository;

/**
 * @author semihbeceren
 *
 */
@Repository
public class JpaCombineRepositoryImpl implements CombineRepository{

	
    @PersistenceContext
    private EntityManager em;
	
	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.repository.CombineRepository#findById(int)
	 */
	@Override
	public Combine findById(int id) throws DataAccessException {
		Query query = this.em.createQuery("SELECT combine FROM Combine combine join fetch combine.items WHERE combine.id =:id");
		query.setParameter("id", id);
		return (Combine)query.getSingleResult();
	}

	
	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.repository.CombineRepository#save(com.semihbeceren.modamda.model.Combine)
	 */
	@Override
	public void save(Combine combine) throws DataAccessException {
		if (combine.getId() == null) {
            this.em.persist(combine);
        } else {
            this.em.merge(combine);
        }
	}
}
