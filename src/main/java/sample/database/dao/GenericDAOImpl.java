package sample.database.dao;

import com.github.vbauer.herald.annotation.Log;
import com.google.inject.TypeLiteral;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by czoeller on 11.07.16.
 */
@SuppressWarnings("unchecked")
public class GenericDAOImpl<E, ID extends Serializable> implements IGenericDAO<E, ID> {

    @Log
    Logger LOG;

    @Inject
    private EntityManager em;
    protected Class<E> daoType;

    @Inject
    @SuppressWarnings("unchecked")
    public GenericDAOImpl(TypeLiteral<E> type) {
        this.daoType = (Class<E>) type.getRawType();
    }

    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public void add(E entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public E saveOrUpdate(E entity) {
        return update(entity);
    }

    @Override
    public E update(E entity) {
        E merged = null;
        try {
            if (!em.getTransaction().isActive()) em.getTransaction().begin();
            merged = em.merge(entity);
            em.getTransaction()
              .commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        Objects.requireNonNull(merged);
        return merged;
    }

    @Override
    public void remove(E entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public void detach(E entity) {
        try {
            if (!em.getTransaction().isActive()) em.getTransaction().begin();
            em.detach(entity);
            em.getTransaction()
              .commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        /*em.getTransaction().begin();
        em.detach(entity);
        em.getTransaction().commit();*/
    }

    @Override
    public E find(ID key) {
        em.getTransaction().begin();
        final E e = (E) em.find(daoType, key);
        em.getTransaction().commit();
        return e;
    }

    @Override
    public List<E> getAll() {
        return em.createQuery("Select t from " + daoType.getSimpleName() + " t").getResultList();
    }

    @Override
    public Optional<E> findFirst(String field, String value) {
        //EasyCriteria<? extends ID> easyCriteria = EasyCriteriaFactory.createQueryCriteria(em, daoType);
        UaiCriteria<E> uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, daoType);
        try {
            return Optional.ofNullable((E) uaiCriteria.andStringLike(field,value).getSingleResult() );
        }
        catch(NoResultException | EntityNotFoundException nre) {
            return Optional.empty();
        }
    }

    @Override
    public E findOrCreate(E entity, String field, String value) {
        final Optional<E> entitySearch = findFirst(field, value);
        if( !entitySearch.isPresent() ) {
            LOG.debug(field + "='" + value + "' not found! Creating: " + entity);
            add( entity );
            return entity;
        } else {
            return entitySearch.get();
        }
    }

    @Override
    public E findOrNew(E entity, String field, String value) {
        final Optional<E> entitySearch = findFirst(field, value);
        if( !entitySearch.isPresent() ) {
            return entity;
        } else {
            return entitySearch.get();
        }
    }

    @Override
    public E findOrCreate(E entity) {
        if ( !em.contains(entity) ) {
            add(entity);
        }
        return entity;
    }
}
