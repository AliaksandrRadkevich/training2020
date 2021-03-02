package by.training.hotel.model.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.training.hotel.model.dao.DaoException;
import by.training.hotel.model.dao.OrderDao;
import by.training.hotel.model.dao.RoomDao;
import by.training.hotel.model.dao.RoomTypeDao;
import by.training.hotel.model.dao.UserDao;
import by.training.hotel.model.domain.order.Order;
import by.training.hotel.model.domain.room.Room;
import by.training.hotel.model.domain.room.RoomType;
import by.training.hotel.model.domain.user.User;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.exception.InvalidDateException;
import by.training.hotel.model.service.exception.InvalidRoomException;
import by.training.hotel.model.service.exception.OrderNotExistsException;
import by.training.hotel.model.service.OrderService;

public class OrderServiceImpl extends BaseService implements OrderService {
    private OrderDao orderDao;
    private UserDao userDao;
    private RoomTypeDao roomTypeDao;
    private RoomDao roomDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoomTypeDao(RoomTypeDao roomTypeDao) {
        this.roomTypeDao = roomTypeDao;
    }

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public List<Order> findAll() throws ServiceException {

        try {
            List<Order> orders = orderDao.readAll();

            for (Order order : orders) {
                User user = order.getUser();
                user = userDao.read(order.getUser().getId());
                order.setUser(user);

                RoomType roomType = order.getRoomType();
                roomType = roomTypeDao.read(order.getRoomType().getId());
                order.setRoomType(roomType);

                Room room = order.getRoom();
                room = roomDao.read(order.getRoom().getId());
                order.setRoom(room);
            }

            return orders;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findById(Long id) throws ServiceException {
        try {
            Order order = orderDao.read(id);
            User user = order.getUser();
            user = userDao.read(order.getUser().getId());
            order.setUser(user);

            RoomType roomType = order.getRoomType();
            roomType = roomTypeDao.read(order.getRoomType().getId());
            order.setRoomType(roomType);

            Room room = order.getRoom();
            room = roomDao.read(order.getRoom().getId());
            order.setRoom(room);

            return order;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Order order) throws ServiceException {
        try {
            getTransaction().start();
            List<Order> conflictingOrders = new ArrayList<Order>();

            for (Order orderItem : orderDao.readAll()) {
                // отбор заказов содержащих комнату как в объекте-параметре "order"
                if (orderItem.getRoom() != null && order.getRoom() != null
                        && order.getRoom().equals(orderItem.getRoom())) {
                    // отбор заказов, не подходящих по диапазону дат для "order"
                    if (order.isIntersect(orderItem)) {
                        // если найденый заказ противоречит "order", добавляем такой заказ в список
                        conflictingOrders.add(orderItem);
                    }
                }
            }

            // проверяем правильность введенных дат (есть баг)
//            if (order.getStartDate().isAfter(LocalDate.now()) & order.getEndDate().isAfter(order.getStartDate())) {
             // проверяем, создавать или обновлять order
                if (order.getId() != null) {
                    // id не пустой, значит обновняем
                    if (order.getRoom() != null) { // проверяем, есть ли комната в объекте-параметре "order"
                        // комната есть, надо проверять пересечения дат, потом сохранять
                        if (conflictingOrders.isEmpty()) {
                            // ничего не противоречит, сохраняем
                            Order storedOrder = orderDao.read(order.getId());
                            if (storedOrder != null) {
                                orderDao.update(order);
                            } else {
                                throw new OrderNotExistsException(order.getId());
                            }
                        } else {
                            // имеются противоречия объекту-параметру "order"
                            throw new InvalidRoomException("Can't assign room id=" + order.getRoom().getId() + ", "
                                    + "orders dates is intersect");
                        }
                    } else {
                        // комнаты нет - просто сохраняем
                        Order storedOrder = orderDao.read(order.getId());
                        if (storedOrder != null) {
                            orderDao.update(order);
                        } else {
                            throw new OrderNotExistsException(order.getId());
                        }
                    }
                } else {
                    // id пустой, значит создаем
                    if (order.getRoom() != null) { // проверяем, есть ли комната в объекте-параметре "order"
                        // комната есть, надо проверять пересечения дат, потом сохранять
                        if (conflictingOrders.isEmpty()) {
                            // ничего не противоречит, сохраняем
                            orderDao.create(order);
                        } else {
                            // имеются противоречия объекту-параметру "order"
                            throw new InvalidRoomException("Can't assign room id=" + order.getRoom().getId() + ", "
                                    + "orders dates is intersect");
                        }
                    } else {
                        // комнаты нет - просто сохраняем
                        Long id = orderDao.create(order);
                        order.setId(id);
                    }
                }
//            } else {
//                throw new InvalidDateException("Check if the date entered is correct");
//            }
            getTransaction().commit();
        } catch (DaoException e) {
            getTransaction().rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            getTransaction().start();

            for (Long id : ids) {
                orderDao.delete(id);
            }

            getTransaction().commit();
        } catch (DaoException e) {
            getTransaction().rollback();
            throw new ServiceException(e);
        }
    }

}
