package ru.net.arh.model.mapped;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//lombok
@Slf4j
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//persitence
@MappedSuperclass
@Access(AccessType.FIELD)
public class NamedBasedEntity extends AbstractBaseEntity {

    public static final String FIND_BY_FIRST_PART_OF_NAME_NAMED_QUERY = "NamedBasedQuery.findByFirstPartOfName";
    @NotNull
//    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name")
    protected String name;

    @JsonIgnore
    @Column(name = "deleted")
    boolean deleted;

    public NamedBasedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public NamedBasedEntity(String name) {
        this.name = name;
    }

}
