package ru.net.arh.to.menu;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class MenuItem {
    private Integer id;
    private int dishId;
    private String dishName;
    private double price;
}
