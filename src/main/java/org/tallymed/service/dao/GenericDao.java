package org.tallymed.service.dao;


import java.util.List;

import org.hibernate.Query;


/***
 * @author Rajdip
 *
 * @param <E>
 * @param <K>
 */
public interface GenericDao<E,K> {
    public void saveOrUpdate(E entity) ;
    public void update(E entity) ;
    public void remove(E entity);
    public E find(K key);
    public List<E> getAll();
    public List<E> findByNamedQuery(Query q);
}
