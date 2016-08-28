
package com.semihbeceren.modamda.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.semihbeceren.modamda.model.Category;
import com.semihbeceren.modamda.model.Combine;
import com.semihbeceren.modamda.model.Item;
import com.semihbeceren.modamda.model.User;
import com.semihbeceren.modamda.util.EntityUtils;


public abstract class AbstractModamdaServiceTests {

    @Autowired
    protected ModamdaService modamdaService;

    @Test
    public void shouldFindUserById() {
        User user = this.modamdaService.findUserById(1);
        assertThat(user.getFirstName()).isEqualTo("George");
    }
    
    @Test
    public void shouldFindCombineById() {
        Combine combine = this.modamdaService.findCombineById(1);
        assertThat(combine.getName()).isEqualTo("comb1");
    }
    
    @Test
    public void shouldFindItemById() {
        Item item = this.modamdaService.findItemById(1);
        assertThat(item.getName()).isEqualTo("Leo-Pant");
    }


    @Test
    @Transactional
    public void shouldInsertUser() {
        User user = new User();
        user.setFirstName("Sam");
        user.setLastName("Schultz");
        user.setAddress("4, Evans Street");
        user.setCity("Wollongong");
        user.setTelephone("4444444444");
        this.modamdaService.saveUser(user);
        assertThat(user.getId().longValue()).isNotEqualTo(0);
    }

    @Test
    @Transactional
    public void shouldUpdateUser() {
        User user = this.modamdaService.findUserById(1);
        String oldLastName = user.getLastName();
        String newLastName = oldLastName + "X";

        user.setLastName(newLastName);
        this.modamdaService.saveUser(user);

        // retrieving new name from database
        user = this.modamdaService.findUserById(1);
        assertThat(user.getLastName()).isEqualTo(newLastName);
    }


    @Test
    public void shouldFindAllCategories() {
        Collection<Category> categories = this.modamdaService.findCategories();

        Category category1 = EntityUtils.getById(categories, Category.class, 1);
        assertThat(category1.getName()).isEqualTo("jean");
        Category category2 = EntityUtils.getById(categories, Category.class, 4);
        assertThat(category2.getName()).isEqualTo("jacket");
    }


}
