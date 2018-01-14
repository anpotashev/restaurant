package ru.net.arh.model.mapped;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

//lombok
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
//persitence
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity extends Object {
    public static final int START_SEQ = 100000;

    @Getter
    @Setter
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id: " + id + "]";
    }

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }


//    public static String getDeleteNamedQueryName() {
//        throw new UnsupportedOperationException("must be overriding on subclasses");
//    }
//
//    public static String getFindAllNamedQueryName() {
//        throw new UnsupportedOperationException("must be overriding on subclasses");
//    }
}
