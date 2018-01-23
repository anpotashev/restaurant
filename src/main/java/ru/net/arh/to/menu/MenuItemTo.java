package ru.net.arh.to.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemTo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private int dishId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String dishName;
    private double price;
}
