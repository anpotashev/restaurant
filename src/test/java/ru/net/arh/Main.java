package ru.net.arh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.net.arh.configuration.SpringConfig;
import ru.net.arh.service.price.PriceService;

import java.time.LocalDate;

@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        PriceService priceService = ctx.getBean(PriceService.class);
        System.out.println("_________________SHOW");
        System.out.println(priceService.getDayMenu(LocalDate.now()));
        System.out.println("_________________SHOW");
        System.out.println(priceService.getDayMenu(LocalDate.now().minusDays(1)));
        System.out.println("_________________SHOW");
        System.out.println(priceService.getDayMenu(LocalDate.now()));
    }

    private static void print(Object object1, Object object2) {

        log.debug("object1: {}, object2: {}, isEquals: {} is=: {}", object1, object2, object1.equals(object2), object1==object2);
    }
}
