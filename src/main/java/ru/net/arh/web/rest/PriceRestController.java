package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.net.arh.to.menu.MenuItem;
import ru.net.arh.web.price.PriceController;

import java.time.LocalDate;
import java.util.List;

/*
GET /restaurants/{restaurantId}/prices/{date} getAllPrices
    return: MenuItem: id, dishId, dishName, price;

PUT /restaurants/{restaurantId}/prices/{date}/{id} - edit price with id;
    return MenuItem: id, dishId, dishName, price;;
    body: MenuItem: dishId, dishName, price, id - null (takes from ${id};

PUT /restaurants/{restaurantId}/prices/{date}/batch - edit prices;
    return MenuItem[]: id, dishId, dishName, price;
    body: MenuItem[]: dishId, dishName, price, id;

POST /restaurants/{restaurantId}/prices/{date}/batch - create new prices;
    return: MenuItem[]: id, dishId, dishName, dishName price;
    body: MenuItem[]: dishId, price, id - null;

POST /restaurants/{restaurantId}/prices/{date} - create new price;
    return: MenuItem: id, dishId, dishName, price;
    body: MenuItem: dishId, price, id - null;

DELETE /restaurants/{restaurantId}/prices/{date}/{id} - delete price with id
    return nothing;

 */
@Slf4j
@RestController
@RequestMapping("/restaurants/{restaurantId}/prices/{date}")
public class PriceRestController {

    @Autowired
    private PriceController priceController;

    @GetMapping
    public ResponseEntity<List<MenuItem>> get(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return ResponseEntity.ok(priceController.get(restaurantId, date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> update(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @PathVariable("id") int id
            , @RequestBody MenuItem menuItem
    ) {
        menuItem.setId(id);
        MenuItem result = priceController.update(restaurantId, date, menuItem).get(0);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/batch")
    public ResponseEntity<List<MenuItem>> update(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @RequestBody MenuItem[] menuItems
    ) {
        List<MenuItem> result = priceController.update(restaurantId, date, menuItems);
        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<MenuItem> create(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @RequestBody MenuItem menuItem
    ) {
        MenuItem result = priceController.create(restaurantId, date, menuItem).get(0);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<MenuItem>> create(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @RequestBody MenuItem[] menuItems
    ) {
        List<MenuItem> result = priceController.create(restaurantId, date, menuItems);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        priceController.delete(id, date);
        return ResponseEntity.noContent().build();
    }

}
