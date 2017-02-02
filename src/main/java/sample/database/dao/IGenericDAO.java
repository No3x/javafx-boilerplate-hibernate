package sample.database.dao;

import java.util.List;
import java.util.Optional;

/**
 * Created by czoeller on 11.07.16.
 */
public interface IGenericDAO<E,K> {
    void add(E entity) ;
    E saveOrUpdate(E entity) ;
    E update(E entity) ;
    void remove(E entity);
    void detach(E entity);
    E find(K key);
    List<E> getAll() ;
    Optional<E> findFirst(String field, String value);
    E findOrCreate(E entity, String field, String value);
    E findOrNew(E entity, String field, String value);
    E findOrCreate(E entity);
}
