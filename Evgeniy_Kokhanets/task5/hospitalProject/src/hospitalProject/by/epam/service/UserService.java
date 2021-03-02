package hospitalProject.by.epam.service;

import java.util.List;

import hospitalProject.by.epam.domain.User;

public interface UserService {
    List<User> findAll() throws ServiceException;
    
    void save(User user) throws ServiceException;
    
    User findById(Long id) throws ServiceException;
    
    User login(String login, String password) throws ServiceException;
    
    void delete(List<Long> ids) throws ServiceException;
}