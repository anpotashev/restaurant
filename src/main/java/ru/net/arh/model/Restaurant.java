package ru.net.arh.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant extends NamedBasedEntity {

    public static final String FIND_ALL = "RESTAURANT.findAll";
    public static final String FIND_BY_NAME_IGNORE_CASE = "RESTAURANT.findByNameIgnoreCase";
    public static final String FIND_BY_NAME = "RESTAURANT.findByName";

    public Restaurant(Integer key, String name) {
        super(key, name);
    }

    public Restaurant(String name) {
        super(name);
    }
}
