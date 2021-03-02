package hospitalProject.by.epam.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import hospitalProject.by.epam.dao.DAOException;
import hospitalProject.by.epam.dao.UserDAO;
import hospitalProject.by.epam.domain.Role;
import hospitalProject.by.epam.domain.User;

public class UserDAOImpl implements UserDAO{
    private Connection connection;
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(User user) throws DAOException {
        String sql = "INSERT INTO `user` (`login`, `password`, `name`, `role`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setInt(4, user.getRole().ordinal());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        }
        catch(SQLException e) {
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
        }
    }

    @Override
    public User read(Long id) throws DAOException {
        String sql = "SELECT `login`, `password`, `name`, `role` FROM `user` WHERE `id` = " + id;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            User user = null;
            if(resultSet.next()){
                user = new User();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
            try {resultSet.close();} catch(Exception e) {}
        }
    }

    @Override
    public void update(User user) throws DAOException {
        String sql = "UPDATE `user` SET `login` = ?, `password` = ?, `name` = ?, `role` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setInt(4, user.getRole().ordinal());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
        }
        catch(SQLException e) {
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        String sql = "DELETE FROM `user` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        catch(SQLException e) {
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
        }
    }

    @Override
    public List<User> readAll() throws DAOException{
        String sql = "SELECT `id`, `login`, `password`, `name`, `role` FROM `user`";
        Statement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
               User user = new User();
               user.setId(resultSet.getLong("id"));
               user.setLogin(resultSet.getString("login"));
               user.setPassword(resultSet.getString("password"));
               user.setName(resultSet.getString("name"));
               user.setRole(Role.values()[resultSet.getInt("role")]);
               users.add(user);
            }
            return users;
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
            try {resultSet.close();} catch(Exception e) {}
        }
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws DAOException {
        String sql = "SELECT `id`, `name`, `role` FROM `user` WHERE `login` = ? AND `password` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(password);
                user.setName(resultSet.getString("name"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
            try {resultSet.close();} catch(Exception e) {}
        }
    }
}
