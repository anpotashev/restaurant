package ru.net.arh.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//lombok
@Slf4j
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//persitence
@MappedSuperclass
@Access(AccessType.FIELD)
public class NamedBasedEntity extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name")
    protected String name;

    public NamedBasedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
    @Override
    public String toString() {
        return super.toString() + " [name: " + name + "]";
    }
}
