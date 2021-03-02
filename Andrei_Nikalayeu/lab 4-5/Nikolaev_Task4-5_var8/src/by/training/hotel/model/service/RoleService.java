package by.training.hotel.model.service;

import java.util.List;

import by.training.hotel.model.domain.user.Role;

public interface RoleService {
    List<Role> findAll() throws ServiceException;
    Role findById(Long id) throws ServiceException;
    void save(Role role) throws ServiceException;
}
