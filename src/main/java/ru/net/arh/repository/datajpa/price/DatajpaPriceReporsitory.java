package ru.net.arh.repository.datajpa.price;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.net.arh.model.Price;
import ru.net.arh.model.PriceId;

import java.time.LocalDate;
import java.util.List;

public interface DatajpaPriceReporsitory extends JpaRepository<Price, PriceId> {

    int deleteByKey(PriceId key);

    List<Price> findAllByKeyDate(LocalDate date);

    Price findByKey(PriceId priceId);
}
