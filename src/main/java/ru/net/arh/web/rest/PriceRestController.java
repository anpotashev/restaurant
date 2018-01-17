package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.net.arh.model.Price;
import ru.net.arh.to.menu.MenuItem;
import ru.net.arh.web.controller.PriceController;

import java.net.URI;
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
@RequestMapping(RootRestController.ROOT_URL + "/restaurants/{restaurantId}/prices")
public class PriceRestController {

    @Autowired
    private PriceController priceController;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getForCurDate(@PathVariable("restaurantId") int restaurantId
    ) {
        return ResponseEntity.ok(priceController.get(restaurantId, LocalDate.now()));
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<MenuItem>> get(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return ResponseEntity.ok(priceController.get(restaurantId, date));
    }

    @GetMapping("/{date}/{id}")
    public ResponseEntity<MenuItem> get(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @PathVariable("id") int priceId
    ) {
        return ResponseEntity.ok(priceController.get(priceId, restaurantId, date));
    }

    @PutMapping("/{date}/{id}")
    public ResponseEntity<Void> update(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @PathVariable("id") int id
            , @RequestBody MenuItem menuItem
    ) {
        menuItem.setId(id);
        Price result = priceController.save(restaurantId, date, menuItem);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{date}")
    public ResponseEntity<Void> create(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @RequestBody MenuItem menuItem
    ) {
        menuItem.setId(null);
        Price created = priceController.save(restaurantId, date, menuItem);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RootRestController.ROOT_URL + "/restaurants/{restaurantId}/prices/{date}/{id}")
                .buildAndExpand(restaurantId, date, created.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{date}/{id}")
    public ResponseEntity<Void> delete(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("id") int id
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        priceController.delete(restaurantId, id, date);
        return ResponseEntity.noContent().build();
    }

}
