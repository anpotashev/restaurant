package ru.net.arh.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.PrimaryKeyGettable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
public abstract class AbstractBasedRepository<T extends PrimaryKeyGettable<K>, K extends Serializable> {

    @PersistenceContext
    protected EntityManager em;

    public abstract Class<T> getClazz();

    public abstract String findAllNamedQuery();

    public T find(final K key) {
        return em.find(getClazz(), key);
    }

    @Transactional
    public T create(final T value) {
        em.persist(value);
        em.flush();
        return value;
    }

    @Transactional
    public T update(final T value) {
        if (find(value.getKey()) == null)
            return null;
        return em.merge(value);
    }

    @Transactional
    public void delete(final K key) {
        T object = em.find(getClazz(), key);
        if (object == null) return;
        em.remove(object);
    }

    public List<T> findAll() {
        return em.createNamedQuery(findAllNamedQuery()).getResultList();
    }

}
