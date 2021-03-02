package by.training.hotel.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.training.hotel.model.dao.DaoException;
import by.training.hotel.model.dao.RoomDao;
import by.training.hotel.model.domain.room.Room;
import by.training.hotel.model.domain.room.RoomType;

public class RoomDaoImpl extends BaseDao implements RoomDao {
    @Override
    public Long create(Room room) throws DaoException {
        String sql = "INSERT INTO `room` (`room_number`, `type_id`, `seats`, `price`) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, room.getRoomNumber());
            statement.setLong(2, room.getRoomType().getId());
            statement.setByte(3, room.getSeats());
            statement.setBigDecimal(4, room.getPrice());
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
    public Room read(Long id) throws DaoException {
        String sql = "SELECT `room_number`, `type_id`, `seats`, `price` FROM `room` WHERE `id` = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Room room = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                room = new Room();
                room.setId(id);
                room.setRoomNumber(resultSet.getString("room_number"));
                room.setRoomType(new RoomType());
                room.getRoomType().setId(resultSet.getLong("type_id"));
                room.setSeats(resultSet.getByte("seats"));
                room.setPrice(resultSet.getBigDecimal("price"));
            }

            return room;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public Room readByRoomNumber(String roomNumber) throws DaoException {
        String sql = "SELECT `id`, `type_id`, `seats`, `price` FROM `room` WHERE `room_number` = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Room room = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, roomNumber);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                room = new Room();
                room.setId(resultSet.getLong("id"));
                room.setRoomNumber(roomNumber);
                room.setRoomType(new RoomType());
                room.getRoomType().setId(resultSet.getLong("type_id"));
                room.setSeats(resultSet.getByte("seats"));
                room.setPrice(resultSet.getBigDecimal("price"));
            }

            return room;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public List<Room> readAll() throws DaoException {
        String sql = "SELECT  `id`, `room_number`, `type_id`, `seats`, `price` FROM `room`";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Room room = null;
        List<Room> rooms = new ArrayList<Room>();

        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                room = new Room();

                room.setId(resultSet.getLong("id"));
                room.setRoomNumber(resultSet.getString("room_number"));
                room.setRoomType(new RoomType());
                room.getRoomType().setId(resultSet.getLong("type_id"));
                room.setSeats(resultSet.getByte("seats"));
                room.setPrice(resultSet.getBigDecimal("price"));

                rooms.add(room);
            }

            return rooms;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public void update(Room room) throws DaoException {
        String sql = "UPDATE `room` SET `room_number` = ?, `type_id` = ?, `seats` = ?, `price` = ? WHERE `id` = ?";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, room.getRoomNumber());
            statement.setLong(2, room.getRoomType().getId());
            statement.setInt(3, room.getSeats());
            statement.setBigDecimal(4, room.getPrice());
            statement.setObject(5, room.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `room` WHERE `id` = ?";

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
