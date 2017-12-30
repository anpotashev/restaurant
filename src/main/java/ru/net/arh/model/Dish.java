package ru.net.arh.model;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import ru.net.arh.model.mapped.NamedBasedEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Slf4j
@Entity
@NoArgsConstructor
@Table(name = "dish")
@SQLDelete(sql = "UPDATE Dish SET deleted=true WHERE id=? ")
@Loader(namedQuery = Dish.FIND_BY_ID)
@NamedQueries({
        @NamedQuery(name = Dish.FIND_BY_ID, query = "SELECT d FROM Dish d WHERE d.id=?1 AND d.deleted=false")
})
public class Dish extends NamedBasedEntity {
    static final String FIND_BY_ID = "Dish.findById";

    public Dish(Integer id, String name) {
        super(id, name);
    }

    public Dish(String name) {
        super(name);
    }
}
