/*
 * 
 */
package com.semihbeceren.modamda.repository;

import org.springframework.dao.DataAccessException;

import com.semihbeceren.modamda.model.Combine;

/**
 * @author semihbeceren
 *
 */
public interface CombineRepository {
	Combine findById(int id) throws DataAccessException;
	void save(Combine combine) throws DataAccessException;
}
