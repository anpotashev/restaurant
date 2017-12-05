package ru.net.arh.service.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Dish;
import ru.net.arh.repository.DishRepository;
import ru.net.arh.service.AbstractNamedServiceImpl;

@Slf4j
@Service
public class DishServiceImpl extends AbstractNamedServiceImpl<Dish> implements DishService {
    @Autowired
    private DishRepository repository;

    protected DishRepository getRepository() {
        return repository;
    }
}
