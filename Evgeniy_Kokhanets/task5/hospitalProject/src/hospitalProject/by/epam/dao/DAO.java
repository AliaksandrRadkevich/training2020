package hospitalProject.by.epam.dao;

import hospitalProject.by.epam.domain.Entity;

public interface DAO <T extends Entity>{
    Long create(T entity) throws DAOException;
    
    T read(Long id) throws DAOException;
    
    void update(T entity) throws DAOException;
    
    void delete(Long id) throws DAOException;
}
