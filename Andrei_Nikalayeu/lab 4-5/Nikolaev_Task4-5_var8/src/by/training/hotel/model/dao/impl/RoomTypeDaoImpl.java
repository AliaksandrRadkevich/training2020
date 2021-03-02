package by.training.hotel.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.training.hotel.model.dao.DaoException;
import by.training.hotel.model.dao.RoomTypeDao;
import by.training.hotel.model.domain.room.RoomType;

public class RoomTypeDaoImpl extends BaseDao implements RoomTypeDao {
    @Override
    public Long create(RoomType roomType) throws DaoException {
        String sql = "INSERT INTO `room_type` (`value`) VALUES (?)";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, roomType.getValue());
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
    public RoomType read(Long id) throws DaoException {
        String sql = "SELECT `value` FROM `room_type` WHERE `id` = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        RoomType roomType = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                roomType = new RoomType();
                roomType.setId(id);
                roomType.setValue(resultSet.getString("value"));
            }

            return roomType;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public List<RoomType> readAll() throws DaoException {
        String sql = "SELECT `id`, `value` FROM `room_type`";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        RoomType roomType = null;

        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            List<RoomType> roomTypes = new ArrayList<RoomType>();

            while (resultSet.next()) {
                roomType = new RoomType();

                roomType.setId(resultSet.getLong("id"));
                roomType.setValue(resultSet.getString("value"));

                roomTypes.add(roomType);
            }

            return roomTypes;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public void update(RoomType roomType) throws DaoException {
        String sql = "UPDATE `room_type` SET `value` = ? WHERE `id` = ?";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, roomType.getValue());
            statement.setLong(2, roomType.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `room_type` WHERE `id` = ?";

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
