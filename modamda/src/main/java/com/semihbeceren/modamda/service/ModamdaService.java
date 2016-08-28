/*
 * 
 */
package com.semihbeceren.modamda.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.semihbeceren.modamda.model.Category;
import com.semihbeceren.modamda.model.Combine;
import com.semihbeceren.modamda.model.Item;
import com.semihbeceren.modamda.model.User;

/**
 * @author semihbeceren
 *
 */
public interface ModamdaService {
	Collection<Category> findCategories() throws DataAccessException;
	User findUserById(int id) throws DataAccessException;
	Combine findCombineById(int id) throws DataAccessException;
	Item findItemById(int id) throws DataAccessException;
	void saveUser(User user) throws DataAccessException;
	void saveCombine(Combine combine) throws DataAccessException;
	
}
