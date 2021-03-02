package by.training.hotel.model.dao;

import java.util.List;

import by.training.hotel.model.domain.user.User;

public interface UserDao extends Dao<User>{
    List<User> readAll() throws DaoException;
    User readByLogin(String login) throws DaoException;
    User readByLoginAndPassword(String login, String password) throws DaoException;
}
