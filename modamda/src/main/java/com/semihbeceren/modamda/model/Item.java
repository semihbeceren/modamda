/*
 * 
 */
package com.semihbeceren.modamda.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author semihbeceren
 *
 */
@Entity
@Table(name = "items")
public class Item extends NamedEntity{
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
