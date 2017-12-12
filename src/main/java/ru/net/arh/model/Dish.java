package ru.net.arh.model;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.mapped.NamedBasedEntity;

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
        @NamedQuery(name = Dish.DELETE_QUERY_NAME, query = "delete from Dish d where d.id = :id")
        , @NamedQuery(name = Dish.FIND_ALL_QUERY_NAME, query = "select d from Dish d order by d.id asc")
        , @NamedQuery(name = Dish.FIND_ALL_BY_FIRST_PART_OF_NAME_QUERY_NAME, query = "select d from Dish d " +
        "where lower(d.name) like lower(concat(:firstPartOfName, '%') ) order by d.id asc")
})
public class Dish extends NamedBasedEntity {
    static final String DELETE_QUERY_NAME = "Dish.delete";
    static final String FIND_ALL_QUERY_NAME = "Dish.findAll";
    static final String FIND_ALL_BY_FIRST_PART_OF_NAME_QUERY_NAME = "Dish.findAllByFirstPartOfName";

    public Dish(Integer id, String name) {
        super(id, name);
    }

    public Dish(String name) {
        super(name);
    }
}
