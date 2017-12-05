package ru.net.arh.model;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Slf4j
@Entity
@NoArgsConstructor
//@ToString
@Table(name = "dish")
public class Dish extends NamedBasedEntity {

    public Dish(Integer key, String name) {
        super(key, name);
    }

    public Dish(String name) {
        super(name);
    }
}
