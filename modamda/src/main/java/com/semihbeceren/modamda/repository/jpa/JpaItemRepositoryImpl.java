/*
 * 
 */
package com.semihbeceren.modamda.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.semihbeceren.modamda.model.Item;
import com.semihbeceren.modamda.repository.ItemRepository;

/**
 * @author semihbeceren
 *
 */
@Repository
public class JpaItemRepositoryImpl implements ItemRepository{

	@PersistenceContext
	private EntityManager em;

	
	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.repository.ItemRepository#findById(int)
	 */
	@Override
	public Item findById(int id) throws DataAccessException {
		return this.em.find(Item.class, id);
	}

}
