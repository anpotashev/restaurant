package ru.net.arh.model;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Slf4j
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant extends NamedBasedEntity {

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
