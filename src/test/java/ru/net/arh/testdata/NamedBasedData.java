package ru.net.arh.testdata;

import ru.net.arh.model.mapped.NamedBasedEntity;

import java.util.List;

public interface NamedBasedData<T extends NamedBasedEntity> {

    T first();

    List<T> all();

    T create(); ///id==null

    T created(); //id!=null

    List<T> allWithCreated();

    T changed();

    List<T> allWithChanged();

    int deleteId();

    List<T> allWithoutDeleted();

    String firstPartOfName();

    List<T> filtered();

}
