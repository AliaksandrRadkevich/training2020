package by.training.hotel.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.training.hotel.model.dao.DaoException;
import by.training.hotel.model.dao.RoleDao;
import by.training.hotel.model.domain.user.Role;

public class RoleDaoImpl extends BaseDao implements RoleDao {
    @Override
    public Long create(Role role) throws DaoException {
        String sql = "INSERT INTO `role` (`value`) VALUES (?)";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, role.getValue());
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public Role read(Long id) throws DaoException {
        String sql = "SELECT `value` FROM `role` WHERE `id` = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Role role = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                role = new Role();
                role.setId(id);
                role.setValue(resultSet.getString("value"));
            }

            return role;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public List<Role> readAll() throws DaoException {
        String sql = "SELECT `id`, `value` FROM `role`";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Role role = null;
        List<Role> roles = new ArrayList<Role>();

        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                role = new Role();

                role.setId(resultSet.getLong("id"));
                role.setValue(resultSet.getString("value"));

                roles.add(role);
            }

            return roles;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public void update(Role role) throws DaoException {
        String sql = "UPDATE `role` SET `value` = ? WHERE `id` = ?";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, role.getValue());
            statement.setLong(2, role.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `role` WHERE `id` = ?";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
    }

}
