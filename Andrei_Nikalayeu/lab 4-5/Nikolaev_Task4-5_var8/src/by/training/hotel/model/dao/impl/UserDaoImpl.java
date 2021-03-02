package by.training.hotel.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.training.hotel.model.dao.DaoException;
import by.training.hotel.model.dao.UserDao;
import by.training.hotel.model.domain.user.Role;
import by.training.hotel.model.domain.user.User;

public class UserDaoImpl extends BaseDao implements UserDao{
    @Override
    public Long create(User user) throws DaoException {
        String sql = "INSERT INTO `user` (`name`, `surname`, `email`, `login`, `password`, `role_id`) " + 
                        "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setObject(3, user.getEmail());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setObject(6, user.getRole().getId());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
        }
    }
    
    @Override
    public User read(Long id) throws DaoException {
        String sql = "SELECT `name`, `surname`, `email`, `login`, `password`, `role_id` FROM `user` WHERE `id` = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(new Role());
                user.getRole().setId(resultSet.getLong("role_id"));
            }

            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
        }
    }
    
    @Override
    public User readByLogin(String login) throws DaoException {
        String sql = "SELECT `id`, `name`, `surname`, `email`, `password`, `role_id` FROM `user` WHERE `login` = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setRole(new Role());
                user.getRole().setId(resultSet.getLong("role_id"));
            }

            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
        }
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws DaoException {
        String sql = "SELECT `id`, `name`, `surname`, `email`, `role_id` FROM `user` WHERE `login` = ? " + 
                        "AND `password` = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(new Role());
                user.getRole().setId(resultSet.getLong("role_id"));
            }

            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
        }
    }

    @Override
    public List<User> readAll() throws DaoException{
        String sql = "SELECT  `id`,`name`, `surname`, `email`, `login`, `password`, `role_id` FROM `user`";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<User>();
        User user = null;

        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(new Role());
                user.getRole().setId(resultSet.getLong("role_id"));

                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        String sql = "UPDATE " + 
                    "`user` SET `name` = ?, `surname` = ?, `email` = ?, `login` = ?, `password` = ?, `role_id` = ? " + 
                    "WHERE `id` = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setObject(6, user.getRole().getId());
            preparedStatement.setLong(7, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `user` WHERE `id` = ?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
    }

}
