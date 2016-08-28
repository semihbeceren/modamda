/*
 * 
 */
package com.semihbeceren.modamda.repository;

import org.springframework.dao.DataAccessException;

import com.semihbeceren.modamda.model.Item;

/**
 * @author semihbeceren
 *
 */
public interface ItemRepository {
	Item findById(int id) throws DataAccessException;
}
