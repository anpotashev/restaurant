package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.net.arh.model.MenuItem;
import ru.net.arh.service.MenuItemService;
import ru.net.arh.to.menu.MenuItemTo;

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
@RequestMapping(RootRest.ROOT_URL + "/restaurants/{restaurantId}/menu_items")
public class MenuItemRestController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<List<MenuItemTo>> getForCurDate(@PathVariable("restaurantId") int restaurantId
    ) {
        return ResponseEntity.ok(menuItemService.getAllForRestorantInDay(LocalDate.now(), restaurantId));
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<MenuItemTo>> get(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return ResponseEntity.ok(menuItemService.getAllForRestorantInDay(date, restaurantId));
    }

    @GetMapping("/{date}/{id}")
    public ResponseEntity<MenuItemTo> get(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @PathVariable("id") int priceId
    ) {
        return ResponseEntity.ok(menuItemService.get(date, restaurantId, priceId));
    }

    @PutMapping("/{date}/{id}")
    public ResponseEntity<Void> update(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @PathVariable("id") int id
            , @RequestBody MenuItemTo menuItemTo
    ) {
        menuItemTo.setId(id);
        menuItemService.save(date, restaurantId, menuItemTo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{date}")
    public ResponseEntity<Void> create(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , @RequestBody MenuItemTo menuItemTo
    ) {
        menuItemTo.setId(null);
        MenuItem created = menuItemService.save(date, restaurantId, menuItemTo);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RootRest.ROOT_URL + "/restaurants/{restaurantId}/menu_items/{date}/{id}")
                .buildAndExpand(restaurantId, date, created.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{date}/{id}")
    public ResponseEntity<Void> delete(@PathVariable("restaurantId") int restaurantId
            , @PathVariable("id") int id
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        menuItemService.delete(date, restaurantId, id);
        return ResponseEntity.noContent().build();
    }

}
