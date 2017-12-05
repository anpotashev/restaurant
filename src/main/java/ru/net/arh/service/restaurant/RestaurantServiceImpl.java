package ru.net.arh.service.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Restaurant;
import ru.net.arh.repository.RestaurantRepository;
import ru.net.arh.service.AbstractNamedServiceImpl;

@Service
public class RestaurantServiceImpl extends AbstractNamedServiceImpl<Restaurant> implements RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    @Override
    protected RestaurantRepository getRepository() {
        return repository;
    }

}
