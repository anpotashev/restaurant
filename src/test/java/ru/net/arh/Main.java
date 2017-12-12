package ru.net.arh;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {

        String fio = "Иванов Иван Иванович, Петров Петр Петрович, Ахмед Хасан";
        String fio1 = fio.replaceAll("([^,] .)[^ ]+", "$1.").replaceAll("(\\.) (.)[^, ]+", "$1$2.");

//        fio.replaceAll("([^,] .)[^.][^ ,]+", "$1.").
        System.out.println(fio1);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
//
//        RestaurantService service= ctx.getBean(RestaurantService.class);
//        Restaurant restaurant = service.get(100005);
//        restaurant.setName("Боброфф");
//        System.out.println("get");
//        service.get(100004);
//        System.out.println("del");
//        service.delete(100003);
//        service.update(restaurant);
//        System.out.println("get");
//        service.get(100004);
//        System.out.println("del");
//        service.delete(100004);

//        System.out.println(service.getAll());
//        DayVotes dayVotes = restaurantService.getDayVotes(LocalDate.now());
//        System.out.println("-------- Votes --------");
//        System.out.println(dayVotes.getDate());
//        dayVotes.getVotes().forEach((restaurant, aLong) -> {
//            System.out.println(restaurant + " - " + aLong);
//                }
//        );
//
//        DayMenu dayMenu = restaurantService.getDayMenu(LocalDate.now());
//        System.out.println("-------- Day menu --------");
//        System.out.println(dayMenu.getDate());
//        dayMenu.getRestarauntMenu().keySet().forEach(restaurant -> {
//            System.out.println(restaurant + ":");
//            dayMenu.getRestarauntMenu().get(restaurant).forEach(restaurantMenuItem -> {
//                System.out.println(restaurantMenuItem.getDish().getName() + " - " + restaurantMenuItem.getPrice());
//                    }
//            );
//        });
        /*List<Restaurant> restaurantsWithPrices = restaurantService.getAllWithPrices(LocalDate.now());
        List<Restaurant> restaurantsWithVotes = restaurantService.getAllWithVotes(LocalDate.now());
        System.out.println(restaurantsWithPrices.size());
        System.out.println(restaurantsWithVotes.size());
        restaurantsWithPrices.forEach(restaurant -> {
            System.out.println(restaurant + " - " + restaurant.getPrices().size());
            restaurant.getPrices().forEach(price -> {
                System.out.print(price.getKey().getDate() + " - ");
                System.out.println(price.getKey().getDish().getName() + " - " + price.getPrice());
            });
        });
        System.out.println("-------------");
        restaurantsWithVotes.forEach(restaurant -> {
            System.out.println(restaurant);
            restaurant.getVotes().forEach(vote -> {
                System.out.println(vote.getKey().getDate());
            });
        }); */
//        PriceServiceImpl priceService = ctx.getBean(PriceServiceImpl.class);
//        System.out.println("_________________SHOW");
//        System.out.println(priceService.getDayMenu(LocalDate.now()));
//        System.out.println("_________________SHOW");
//        System.out.println(priceService.getDayMenu(LocalDate.now().minusDays(1)));
//        System.out.println("_________________SHOW");
//        System.out.println(priceService.getDayMenu(LocalDate.now()));
    }

    private static void print(Object object1, Object object2) {

        log.debug("object1: {}, object2: {}, isEquals: {} is=: {}", object1, object2, object1.equals(object2), object1 == object2);
    }
}
