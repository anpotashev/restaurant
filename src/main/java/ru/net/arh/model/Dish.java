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
    public Dish(Integer id, String name) {
        super(id, name);
    }
}
