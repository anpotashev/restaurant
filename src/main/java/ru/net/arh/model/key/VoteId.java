package ru.net.arh.model.key;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Slf4j
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VoteId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "date")
    @NotNull
//    @NotBlank
    LocalDate date;

}
