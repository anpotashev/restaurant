package ru.net.arh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.net.arh.configuration.SpringConfig;
import ru.net.arh.configuration.SpringDBConfig;
import ru.net.arh.configuration.SpringTestConfig;
import ru.net.arh.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringDBConfig.class);
//
//        Dish dish = ctx.getBean(Dish.class);
//        Dish dish2 = ctx.getBean(Dish.class);
//        print(dish, dish2);
//
//        Restaurant restaurant = new Restaurant(1, "Restaraunt");
//        Restaurant restaurant2 = new Restaurant(1, "Restaraunt2");
//        print(restaurant, restaurant2);
//
//        Price price = new Price(restaurant, dish, LocalDate.now(), new BigDecimal(10.0) );
//        Price price2 = new Price(restaurant, dish, LocalDate.now(), new BigDecimal(10.0) );
//        print(price, price2);
//
//        User user = new User(1, "name", "email", "password", Role.ROLE_ADMIN, Role.ROLE_USER);
//        User user2 = new User(1, "name", "email", "password", Role.ROLE_ADMIN);
//        print(user, user2);
//
//        Vote vote = new Vote(user, LocalDate.now(), restaurant);
//        Vote vote2 = new Vote(user, LocalDate.now(), restaurant);
//        print(vote, vote2);

    }

    private static void print(Object object1, Object object2) {

        log.debug("object1: {}, object2: {}, isEquals: {} is=: {}", object1, object2, object1.equals(object2), object1==object2);
    }
}
