package ru.net.arh.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ru.net.arh.model.mapped.NamedBasedEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NoArgsConstructor
@Entity
//@Table(name = "restaurant")
@SQLDelete(sql = "UPDATE Restaurant SET deleted=true WHERE id=? ")
@Loader(namedQuery = Restaurant.FIND_BY_ID)
@Where(clause = "deleted=false")
@NamedQueries({
        @NamedQuery(name = Restaurant.FIND_BY_ID, query = "SELECT r FROM Restaurant r WHERE r.id=?1 AND r.deleted=false")
})
public class Restaurant extends NamedBasedEntity {
    static final String FIND_BY_ID = "Restaurant.findById";

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(String name) {
        super(name);
    }

}
