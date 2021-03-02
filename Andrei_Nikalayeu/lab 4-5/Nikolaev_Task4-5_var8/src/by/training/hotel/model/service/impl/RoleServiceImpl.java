package by.training.hotel.model.service.impl;

import java.util.List;

import by.training.hotel.model.dao.DaoException;
import by.training.hotel.model.dao.RoleDao;
import by.training.hotel.model.domain.user.Role;
import by.training.hotel.model.service.RoleService;
import by.training.hotel.model.service.ServiceException;

public class RoleServiceImpl extends BaseService implements RoleService{
    private RoleDao roleDao;
    
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findAll() throws ServiceException {
        try {
            return roleDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    
    @Override
    public Role findById(Long id) throws ServiceException {
        try {
            return roleDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Role role) throws ServiceException {
        try {
            getTransaction().start();
            
            if (role.getId() != null) {
                roleDao.update(role);
            } else {
                roleDao.create(role);
            }
            
            getTransaction().commit();
        } catch (DaoException e) {
            getTransaction().rollback(); 
            throw new ServiceException(e);
        }
    }
}
