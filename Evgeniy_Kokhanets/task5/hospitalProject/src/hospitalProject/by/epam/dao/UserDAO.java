package hospitalProject.by.epam.dao;

import java.util.List;

import hospitalProject.by.epam.domain.User;

public interface UserDAO extends DAO<User>{
    List<User> readAll() throws DAOException;
    
    User readByLoginAndPassword(String login, String password) throws DAOException;
}
