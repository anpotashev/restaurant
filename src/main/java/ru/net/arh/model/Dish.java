package ru.net.arh.model;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Slf4j
@Entity
@NoArgsConstructor
//@ToString
@Table(name = "dish")
@NamedQueries({
        @NamedQuery(name = "DISH.findByNameIgnoreCase", query = "select d from Dish d where lower(d.name) like concat(lower(:name), '%' ) ")
        , @NamedQuery(name = "DISH.findByName", query = "select d from Dish d where d.name like concat(:name, '%' ) ")
        , @NamedQuery(name = "DISH.findAll", query = "select d from Dish d order by d.id")
})
public class Dish extends NamedBasedEntity {
    public static final String FIND_BY_NAME_IGNORE_CASE = "DISH.findByNameIgnoreCase";
    public static final String FIND_BY_NAME = "DISH.findByName";
    public static final String FIND_ALL = "DISH.findAll";

    public Dish(Integer id, String name) {
        super(id, name);
    }

    public Dish(String name) {
        super(name);
    }
}
