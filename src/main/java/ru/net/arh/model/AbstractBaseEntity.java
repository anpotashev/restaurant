package ru.net.arh.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

//lombok
@Slf4j
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
//persitence
@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractBaseEntity implements PrimaryKeyGettable<Integer> {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

//    public boolean isNew() {
//        return this.id == null;
//    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id: " + id + "]";
    }

    @Override
    public Integer getKey() {
        return id;
    }
}
