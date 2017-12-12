package ru.net.arh.repository.jpa;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.net.arh.model.DeleteNamedQueryExists;
import ru.net.arh.model.mapped.AbstractBaseEntity;
import ru.net.arh.model.mapped.NamedBasedEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NamedQueriesUtil {

    public static String getDeleteNamedQuery(final Class<? extends DeleteNamedQueryExists> clazz) {
        return clazz.getSimpleName().toString() + ".delete";
    }

    public static String getFindAllNamedQuery(Class<? extends AbstractBaseEntity> clazz) {
        return clazz.getSimpleName().toString() + ".findAll";
    }

    public static String getFindAllByFirstPartOfNameNamedQuery(Class<? extends NamedBasedEntity> clazz) {
        return clazz.getSimpleName().toString() + ".findAllByFirstPartOfName";
    }
}
