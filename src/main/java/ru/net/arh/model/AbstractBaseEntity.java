package ru.net.arh.model;

import lombok.*;

import javax.persistence.*;

//lombok
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
//persitence
@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractBaseEntity implements PrimaryKeyGettable<Integer> {
    public static final int START_SEQ = 100000;

    @Getter
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer key;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [key: " + key + "]";
    }

}
