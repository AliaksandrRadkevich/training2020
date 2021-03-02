package by.training.hotel.model.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hotel.model.dao.DaoException;
import by.training.hotel.model.dao.RoleDao;
import by.training.hotel.model.dao.UserDao;
import by.training.hotel.model.domain.user.Role;
import by.training.hotel.model.domain.user.User;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.UserService;
import by.training.hotel.model.service.exception.UserLoginNotUniqueException;
import by.training.hotel.model.service.exception.UserNotExistsException;

public class UserServiceImpl extends BaseService implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;
    private static Logger log = LogManager.getLogger();

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            List<User> users = userDao.readAll();

            for (User user : users) {
                Role role = user.getRole();
                role = roleDao.read(user.getRole().getId());
                user.setRole(role);
            }
            log.debug("all users");
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {

            User user = userDao.read(id);
            Role role = user.getRole();
            role = roleDao.read(user.getRole().getId());
            user.setRole(role);
            return user;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User login(String login, String password) throws ServiceException {
        try {
            User user = userDao.readByLoginAndPassword(login, password);
            if (user != null) {
                Role role = user.getRole();
                role = roleDao.read(user.getRole().getId());
                user.setRole(role);
            }
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            getTransaction().start();

            if (user.getId() != null) {
                User storedUser = userDao.read(user.getId());
                if (storedUser != null) {
                    if (storedUser.getLogin().equals(user.getLogin()) || userDao.readByLogin(user.getLogin()) == null) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginNotUniqueException(user.getLogin());
                    }
                } else {
                    throw new UserNotExistsException(user.getId());
                }
            } else {
                if (userDao.readByLogin(user.getLogin()) == null) {
                    Long id = userDao.create(user);
                    user.setId(id);
                } else {
                    throw new UserLoginNotUniqueException(user.getLogin());
                }
            }

            getTransaction().commit();
        } catch (DaoException e) {
            getTransaction().rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            getTransaction().start();

            for (Long id : ids) {
                userDao.delete(id);
            }

            getTransaction().commit();
        } catch (DaoException e) {
            getTransaction().rollback();
            throw new ServiceException(e);
        }
    }

}
