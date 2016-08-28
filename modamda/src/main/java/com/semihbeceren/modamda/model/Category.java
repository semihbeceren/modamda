/*
 * 
 */
package com.semihbeceren.modamda.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 * @author semihbeceren
 *
 */
@Entity
@Table(name = "categories")
public class Category extends NamedEntity {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER)
	private Set<Item> items;
	
	protected Set<Item> getItemsInternal(){
		if(this.items == null){
			this.items = new HashSet<>();
		}
		
		return this.items;
	}
	
	protected void setItemsInternal(Set<Item> items){
		this.items = items;
	}
	
	public List<Item> getItems(){
		List<Item> sortedItems = new ArrayList<>(getItemsInternal());
		PropertyComparator.sort(sortedItems, new MutableSortDefinition("name", false, false));
		return Collections.unmodifiableList(sortedItems);
	}
	
	public void addItem(Item item){
		getItemsInternal().add(item);
		item.setCategory(this);
	}
	
}
