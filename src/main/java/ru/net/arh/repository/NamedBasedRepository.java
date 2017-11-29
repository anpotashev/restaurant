package ru.net.arh.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.NamedBasedEntity;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
public abstract class NamedBasedRepository<T extends NamedBasedEntity> extends AbstractBasedRepository<T, Integer> {

    protected abstract String findByNameNamedQueryIgnoreCase();

    protected abstract String findByNameNamedQuery();

    public List<T> findByNameIgnoreCase(String name) {

        return super.em.createNamedQuery(findByNameNamedQueryIgnoreCase()).setParameter("name", name).getResultList();
    }

    public List<T> findByName(String name) {
        return super.em.createNamedQuery(findByNameNamedQuery()).setParameter("name", name).getResultList();
    }

}
