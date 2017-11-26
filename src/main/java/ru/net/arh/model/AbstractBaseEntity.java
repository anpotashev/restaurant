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
public class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id: " + id + "]";
    }
}
