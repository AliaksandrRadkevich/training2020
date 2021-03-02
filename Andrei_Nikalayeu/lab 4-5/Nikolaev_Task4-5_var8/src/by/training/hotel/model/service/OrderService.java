package by.training.hotel.model.service;

import java.util.List;

import by.training.hotel.model.domain.order.Order;

public interface OrderService{
    List<Order> findAll() throws ServiceException;   
    Order findById(Long id) throws ServiceException;
    void save(Order order) throws ServiceException;
    void delete(List<Long> ids) throws ServiceException;
}
