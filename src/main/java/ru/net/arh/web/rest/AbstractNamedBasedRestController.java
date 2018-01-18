package ru.net.arh.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.net.arh.model.mapped.NamedBasedEntity;
import ru.net.arh.service.AbstractNamedService;

import java.net.URI;
import java.util.List;

public abstract class AbstractNamedBasedRestController<T extends NamedBasedEntity> {

    protected abstract AbstractNamedService<T> gerService();

    public abstract String getUri();

    @GetMapping
    public ResponseEntity<List<T>> get() {
        return ResponseEntity.ok(gerService().getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable("id") int id) {
        return ResponseEntity.ok(gerService().get(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<T>> get(@RequestParam("name") String name) {
        return ResponseEntity.ok(gerService().findAllByFirstPartOfNameIgnoringCase(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        gerService().delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody T entity) {
        T created = gerService().save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(getUri() + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable("id") Integer id, @RequestBody T entity) {
        entity.setId(id);
        gerService().save(entity);
        return ResponseEntity.noContent().build();
    }

}
