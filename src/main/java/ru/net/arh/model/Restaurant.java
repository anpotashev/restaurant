package ru.net.arh.model;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Slf4j
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
@NamedQueries({
        @NamedQuery(name = "RESTAURANT.findByNameIgnoreCase", query = "select r from Restaurant r where lower(r.name) like concat(lower(:name), '%' ) ")
        , @NamedQuery(name = "RESTAURANT.findByName", query = "select r from Restaurant r where r.name like concat(:name, '%' ) ")
        , @NamedQuery(name = "RESTAURANT.findAll", query = "select r from Restaurant r order by r.id")
})
public class Restaurant extends NamedBasedEntity {

    public static final String FIND_ALL = "RESTAURANT.findAll";
    public static final String FIND_BY_NAME_IGNORE_CASE = "RESTAURANT.findByNameIgnoreCase";
    public static final String FIND_BY_NAME = "RESTAURANT.findByName";

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(String name) {
        super(name);
    }
}
