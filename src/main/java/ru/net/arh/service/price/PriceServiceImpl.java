package ru.net.arh.service.price;

import org.springframework.stereotype.Service;
import ru.net.arh.to.menu.DayMenu;

import java.time.LocalDate;

@Service
public class PriceServiceImpl extends AbstractPriceService {

    @Override
    public DayMenu getDayMenu() {
        return super.getDayMenu(LocalDate.now());
    }
}
