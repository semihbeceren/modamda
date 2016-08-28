/*
 * 
 */
package com.semihbeceren.modamda.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semihbeceren.modamda.model.Category;
import com.semihbeceren.modamda.model.Combine;
import com.semihbeceren.modamda.model.Item;
import com.semihbeceren.modamda.model.User;
import com.semihbeceren.modamda.repository.CategoryRepository;
import com.semihbeceren.modamda.repository.CombineRepository;
import com.semihbeceren.modamda.repository.ItemRepository;
import com.semihbeceren.modamda.repository.UserRepository;

/**
 * @author semihbeceren
 *
 */
@Service
public class ModamdaServiceImpl implements ModamdaService{

	
	private CategoryRepository categoryRepository;
	private CombineRepository combineRepository;
	private ItemRepository itemRepository;
	private UserRepository userRepository;
	
	
	@Autowired
	public ModamdaServiceImpl(CategoryRepository categoryRepository, CombineRepository combineRepository, ItemRepository itemRepository, UserRepository userRepository) {
		this.categoryRepository = categoryRepository;
		this.combineRepository = combineRepository;
		this.itemRepository = itemRepository;
		this.userRepository = userRepository;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.service.ModamdaService#findCategories()
	 */
	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "categories")
	public Collection<Category> findCategories() throws DataAccessException {
		return categoryRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.service.ModamdaService#findUserById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public User findUserById(int id) throws DataAccessException {
		return userRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.service.ModamdaService#findCombineById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Combine findCombineById(int id) throws DataAccessException {
		return combineRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.service.ModamdaService#findItemById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Item findItemById(int id) throws DataAccessException {
		return itemRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.service.ModamdaService#saveUser(com.semihbeceren.modamda.model.User)
	 */
	@Override
	@Transactional
	public void saveUser(User user) throws DataAccessException {
		userRepository.save(user);
	}

	/* (non-Javadoc)
	 * @see com.semihbeceren.modamda.service.ModamdaService#saveCombine(com.semihbeceren.modamda.model.Combine)
	 */
	@Override
	@Transactional
	public void saveCombine(Combine combine) throws DataAccessException {
		combineRepository.save(combine);
	}

}
