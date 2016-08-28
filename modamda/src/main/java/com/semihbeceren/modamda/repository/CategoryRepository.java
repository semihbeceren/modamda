/*
 * 
 */
package com.semihbeceren.modamda.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.semihbeceren.modamda.model.Category;

/**
 * @author semihbeceren
 *
 */
public interface CategoryRepository {
	List<Category> findAll() throws DataAccessException;
}
