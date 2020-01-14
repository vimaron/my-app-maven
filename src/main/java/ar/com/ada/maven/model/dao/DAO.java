package ar.com.ada.maven.model.dao;

import ar.com.ada.maven.model.dto.ContinentDTO;

import java.util.Collection;

public interface DAO<T> {

    Collection<T> findAll();

    T findById(Integer id);

    Boolean save(T t);

    Boolean update(T t, Integer id);

    Boolean delete(Integer id);

}
