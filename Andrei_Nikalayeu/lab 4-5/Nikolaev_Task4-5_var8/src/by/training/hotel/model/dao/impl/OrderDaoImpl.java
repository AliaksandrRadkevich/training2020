package by.training.hotel.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import by.training.hotel.model.dao.DaoException;
import by.training.hotel.model.dao.OrderDao;
import by.training.hotel.model.domain.order.Order;
import by.training.hotel.model.domain.room.Room;
import by.training.hotel.model.domain.room.RoomType;
import by.training.hotel.model.domain.user.User;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public Long create(Order order) throws DaoException {
        String sql = "INSERT INTO `order` (`creation_date`, `user_id`, `room_seats`, `type_id`, `start_date`, "
                + "`end_date`, `room_id`) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setObject(1, order.getCreationDate());
            statement.setObject(2, order.getUser().getId());
            statement.setByte(3, order.getRoomSeats());
            statement.setObject(4, order.getRoomType().getId());
            statement.setObject(5, order.getStartDate());
            statement.setObject(6, order.getEndDate());

            if (order.getRoom() != null) {
                statement.setObject(7, order.getRoom().getId());
            } else {
                statement.setNull(7, Types.BIGINT);
            }

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
    public Order read(Long id) throws DaoException {
        String sql = "SELECT " + 
                        "`creation_date`, `user_id`, `room_seats`, `type_id`, `start_date`, `end_date`, `room_id`" + 
                        " FROM `order` WHERE `id` = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Order order = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                order = new Order();

                order.setId(id);
                order.setCreationDate(LocalDateTime.parse(resultSet.getString("creation_date"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                order.setUser(new User());
                order.getUser().setId(resultSet.getLong("user_id"));
                order.setRoomSeats(resultSet.getByte("room_seats"));
                order.setRoomType(new RoomType());
                order.getRoomType().setId(resultSet.getLong("type_id"));
                order.setStartDate(LocalDate.parse(resultSet.getString("start_date")));
                order.setEndDate(LocalDate.parse(resultSet.getString("end_date")));
                order.setRoom(new Room());
                order.getRoom().setId(resultSet.getLong("room_id"));
            }

            return order;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public List<Order> readAll() throws DaoException {
        String sql = "SELECT " + 
                    "`id`, `creation_date`, `user_id`, `room_seats`, `type_id`, `start_date`, `end_date`, `room_id` " + 
                    "FROM `order`";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Order order = null;
        List<Order> orders = new ArrayList<Order>();

        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                order = new Order();

                order.setId(resultSet.getLong("id"));
                order.setCreationDate(LocalDateTime.parse(resultSet.getString("creation_date"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                order.setUser(new User());
                order.getUser().setId(resultSet.getLong("user_id"));
                order.setRoomSeats(resultSet.getByte("room_seats"));
                order.setRoomType(new RoomType());
                order.getRoomType().setId(resultSet.getLong("type_id"));
                order.setStartDate(LocalDate.parse(resultSet.getString("start_date")));
                order.setEndDate(LocalDate.parse(resultSet.getString("end_date")));
                order.setRoom(new Room());
                order.getRoom().setId(resultSet.getLong("room_id"));

                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
    }

    @Override
    public void update(Order order) throws DaoException {
        String sql = "UPDATE `order` " + 
                        "SET `creation_date` = ?, `user_id` = ?, `room_seats` = ?, " + 
                        "`type_id` = ?, `start_date` = ?, `end_date` = ?, `room_id` = ? " + 
                        "WHERE `id` = ?";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setObject(1, order.getCreationDate());
            statement.setObject(2, order.getUser().getId());
            statement.setByte(3, order.getRoomSeats());
            statement.setObject(4, order.getRoomType().getId());
            statement.setObject(5, order.getStartDate());
            statement.setObject(6, order.getEndDate());

            if (order.getRoom() != null) {
                statement.setObject(7, order.getRoom().getId());
            } else {
                statement.setNull(7, Types.BIGINT);
            }

            statement.setObject(8, order.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `order` WHERE `id` = ?";

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
