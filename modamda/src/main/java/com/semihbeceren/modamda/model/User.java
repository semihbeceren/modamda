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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 * @author semihbeceren
 *
 */
@Entity
@Table(name = "users")
public class User extends Person{
	
	@Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Combine> combines;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

    protected Set<Combine> getCombinesInternal(){
    	if(this.combines == null){
    		this.combines = new HashSet<>();
    	}
    	
    	return this.combines;
    }
    
    protected void setCombinesInternal(Set<Combine> combines){
    	this.combines = combines;
    }
    
    public List<Combine> getCombines(){
    	List<Combine> sortedCombines = new ArrayList<>(getCombinesInternal());
    	PropertyComparator.sort(sortedCombines, new MutableSortDefinition("add_date", false, false));
    	return Collections.unmodifiableList(sortedCombines);
    }
    
    public void addCombine(Combine combine){
    	getCombinesInternal().add(combine);
    	combine.setUser(this);
    }
    
    public int getNumberOfCombines(){
    	return getCombinesInternal().size();
    }

}
