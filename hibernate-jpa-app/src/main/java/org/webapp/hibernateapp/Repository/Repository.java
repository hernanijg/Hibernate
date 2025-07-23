package org.webapp.hibernateapp.Repository;

import java.util.List;

public interface Repository <T> {
    List<T> list();
    T forId(Long id);
    void save(T t);
    void delete(Long id);

}
