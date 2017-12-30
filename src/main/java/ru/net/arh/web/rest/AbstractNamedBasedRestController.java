package ru.net.arh.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.net.arh.model.mapped.NamedBasedEntity;
import ru.net.arh.utils.validation.exception.ValidationException;
import ru.net.arh.web.controller.AbstractNamedController;

import java.util.List;

public abstract class AbstractNamedBasedRestController<T extends NamedBasedEntity> {

    public abstract AbstractNamedController<T> getController();

    @GetMapping
    public ResponseEntity<List<T>> get() {
        return ResponseEntity.ok(getController().getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable("id") int id) {
        return ResponseEntity.ok(getController().get(id));
    }

    @GetMapping("?name={name}")
    public ResponseEntity<List<T>> get(@PathVariable("name") String name) {
        return ResponseEntity.ok(getController().getByFirstPartOfName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        getController().delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody T entity) {
        checkId(entity);
        getController().save(entity);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable("id") int id, @RequestBody T entity) {
        checkId(id, entity);
        getController().save(entity);
        return ResponseEntity.noContent().build();
    }

    private void checkId(T entity) {
        if (entity.getId() != null) {
            throw new ValidationException("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private void checkId(int id, T entity) {
        if (entity.getId() != id) {
            throw new ValidationException("id must be " + id, HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
