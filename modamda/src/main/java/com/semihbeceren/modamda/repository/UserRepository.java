/*
 * 
 */
package com.semihbeceren.modamda.repository;

import org.springframework.dao.DataAccessException;

import com.semihbeceren.modamda.model.User;

/**
 * @author semihbeceren
 *
 */
public interface UserRepository {

	User findById(int id) throws DataAccessException;
	void save(User user) throws DataAccessException;
	
}
