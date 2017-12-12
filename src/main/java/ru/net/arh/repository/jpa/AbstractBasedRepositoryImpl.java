package ru.net.arh.repository.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.User;
import ru.net.arh.model.mapped.AbstractBaseEntity;
import ru.net.arh.repository.AbstractBasedRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
@Transactional(readOnly = true)
public abstract class AbstractBasedRepositoryImpl<T extends AbstractBaseEntity> implements AbstractBasedRepository<T> {

    @PersistenceContext
    protected EntityManager em;

    protected abstract Class<T> getEntityClass();

    @Override
    public T find(int id) {
        return em.find(getEntityClass(), id);
    }

    @Override
    @Transactional
    public T create(T value) {
        em.persist(value);
        return value;
    }

    @Override
    @Transactional
    public T update(T value) {
        if (find(value.getId()) == null)
            return null;
        return em.merge(value);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        String namedQueryName = NamedQueriesUtil.getDeleteNamedQuery(getEntityClass());
        return em.createNamedQuery(namedQueryName)
                .setParameter("id", id)
                .executeUpdate() > 0;
    }

    @Override
    public List<T> findAll() {
        String namedQueryName = NamedQueriesUtil.getFindAllNamedQuery(getEntityClass());
        return em.createNamedQuery(namedQueryName)
                .getResultList();
    }

    public static class UserRepository extends AbstractBasedRepositoryImpl<User> implements ru.net.arh.repository.UserRepository {

        @Override
        protected Class<User> getEntityClass() {
            return User.class;
        }
    }
}
