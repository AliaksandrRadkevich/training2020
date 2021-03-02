package by.training.hotel.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class BaseDao {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {}
        }
    }

    protected static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {}
        }
    }
}
