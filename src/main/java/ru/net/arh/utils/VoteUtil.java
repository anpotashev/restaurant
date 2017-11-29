package ru.net.arh.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class VoteUtil {

    @Getter
    @Setter
    private static LocalTime canRevoteUtilTime = LocalTime.of(11, 0);

    public static boolean canRevote() {
        return LocalTime.now().isBefore(canRevoteUtilTime);
    }
}
