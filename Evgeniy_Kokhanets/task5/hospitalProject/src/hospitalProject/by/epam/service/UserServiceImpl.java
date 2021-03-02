package hospitalProject.by.epam.service;

import java.util.List;

import hospitalProject.by.epam.dao.DAOException;
import hospitalProject.by.epam.dao.UserDAO;
import hospitalProject.by.epam.domain.User;

public class UserServiceImpl implements UserService{
    private UserDAO userDao;
    
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.read(id);
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            if(user.getId() != null) {
                userDao.update(user);
            }
            else {
                userDao.create(user);
            }
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }
    

    @Override
    public User login(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for(Long id : ids) {
                userDao.delete(id);
            }
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
    }
}
