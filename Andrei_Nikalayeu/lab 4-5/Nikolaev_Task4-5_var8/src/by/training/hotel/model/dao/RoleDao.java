package by.training.hotel.model.dao;

import java.util.List;

import by.training.hotel.model.domain.user.Role;

public interface RoleDao extends Dao<Role>{
    List<Role> readAll() throws DaoException;
}
