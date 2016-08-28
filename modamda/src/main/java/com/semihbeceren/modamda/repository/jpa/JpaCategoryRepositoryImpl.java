/*
 * 
 */
package com.semihbeceren.modamda.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.semihbeceren.modamda.model.Category;
import com.semihbeceren.modamda.repository.CategoryRepository;

/**
 * @author semihbeceren
 *
 */
@Repository
public class JpaCategoryRepositoryImpl implements CategoryRepository{

	
	@PersistenceContext
    private EntityManager em;
	
	
	
	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.repository.CategoryRepository#findAll()
	 */
	@Override
	@Cacheable(value = "categories")
    @SuppressWarnings("unchecked")
	public List<Category> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT distinct category FROM Category category left join fetch category.items ORDER BY category.name").getResultList();
	}

}
