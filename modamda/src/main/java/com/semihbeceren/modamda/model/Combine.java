/*
 * 
 */
package com.semihbeceren.modamda.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author semihbeceren
 *
 */
@Entity
@Table(name = "combines")
public class Combine extends NamedEntity{

	@Column(name = "add_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime addDate;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "combined_items", joinColumns = @JoinColumn(name = "combine_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private Set<Item> items;

	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public LocalDateTime getAddDate() {
		return addDate;
	}

	public void setAddDate(LocalDateTime addDate) {
		this.addDate = addDate;
	}
	
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
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
