package ru.net.arh.model.mapped;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@MappedSuperclass
@Access(AccessType.FIELD)

public abstract class AbstractBaseEntity extends Object {
    public static final int START_SEQ = 100000;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected Integer id;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id: " + id + "]";
    }

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

}
