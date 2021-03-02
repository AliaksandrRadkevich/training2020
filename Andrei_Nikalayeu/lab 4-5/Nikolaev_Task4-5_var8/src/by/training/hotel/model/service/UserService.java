package by.training.hotel.model.service;

import java.util.List;

import by.training.hotel.model.domain.user.User;

public interface UserService{
    List<User> findAll() throws ServiceException;   
    User findById(Long id) throws ServiceException;
    User login(String login, String password) throws ServiceException;
    void save(User user) throws ServiceException;
    void delete(List<Long> ids) throws ServiceException;
}
