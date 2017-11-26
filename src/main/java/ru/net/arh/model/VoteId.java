package ru.net.arh.model;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Slf4j
@Embeddable
class VoteId {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "date")
    @NotNull
    @NotBlank
    LocalDate date;

}
