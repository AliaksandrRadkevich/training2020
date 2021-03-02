package by.training.hotel.model.dao;

import java.util.List;

import by.training.hotel.model.domain.order.Order;

public interface OrderDao extends Dao<Order>{
    List<Order> readAll() throws DaoException;
}
