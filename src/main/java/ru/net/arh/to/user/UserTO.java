package ru.net.arh.to.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTO {
    @NotNull
    @Size(min = 2, max = 64)
    String username;

    @NotNull
    @Size(min = 5, max = 64)
    String password;

}
