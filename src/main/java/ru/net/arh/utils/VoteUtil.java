package ru.net.arh.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

@Slf4j
public class VoteUtil {

    @Getter
    @Setter
    private static LocalTime usersCanChangeVotetime = LocalTime.of(11, 0);
}
