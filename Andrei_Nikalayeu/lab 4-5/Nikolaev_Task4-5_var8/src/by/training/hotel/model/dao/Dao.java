package by.training.hotel.model.dao;

import by.training.hotel.model.domain.Entity;

public interface Dao<T extends Entity> {
    Long create(T entity) throws DaoException;
    T read(Long id) throws DaoException;
    void update(T entity) throws DaoException;
    void delete(Long id) throws DaoException;
}
