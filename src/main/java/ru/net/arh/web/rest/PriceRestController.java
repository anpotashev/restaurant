package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.net.arh.to.menu.MenuItem;
import ru.net.arh.web.controller.PriceController;

import java.time.LocalDate;
import java.util.List;

/*
GET /restaurants/{restaurantId}/prices/{date} getAllPrices
    return: MenuItem: id, dishId, dishName, price;

PUT /restaurants/{restaurantId}/prices/{date}/{id} - edit price with id;
    return MenuItem: id, dishId, dishName, price;;
    body: MenuItem: dishId, dishName, price, id - null (takes from ${id};

POST /restaurants/{restaurantId}/prices/{date} - create new price;
    return: MenuItem: id, dishId, dishName, price;
    body: MenuItem: dishId, price, id - null;

DELETE /restaurants/{restaurantId}/prices/{date}/{id} - delete price with id
    return nothing;

 */
@Slf4j
@RestController
@RequestMapping(RootRestController.ROOT_URL + "/restaurants/{restaurantId}/prices/{date}")
public class PriceRestController {

    @Autowired
    private PriceController priceController;

    @GetMapping
    public ResponseEntity<List<MenuItem>> get(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return ResponseEntity.ok(priceController.get(restaurantId, date));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> get(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @PathVariable("id") int priceId
    ) {
        return ResponseEntity.ok(priceController.get(priceId, restaurantId, date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> update(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @PathVariable("id") int id
            , @RequestBody MenuItem menuItem
    ) {
        menuItem.setId(id);
        MenuItem result = priceController.save(restaurantId, date, menuItem);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<MenuItem> create(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @RequestBody MenuItem menuItem
    ) {
        menuItem.setId(null);
        MenuItem result = priceController.save(restaurantId, date, menuItem);
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
