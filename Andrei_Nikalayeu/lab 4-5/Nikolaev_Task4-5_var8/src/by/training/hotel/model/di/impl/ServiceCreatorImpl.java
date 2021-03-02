package by.training.hotel.model.di.impl;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hotel.model.dao.OrderDao;
import by.training.hotel.model.dao.RoleDao;
import by.training.hotel.model.dao.RoomDao;
import by.training.hotel.model.dao.RoomTypeDao;
import by.training.hotel.model.dao.UserDao;
import by.training.hotel.model.dao.impl.OrderDaoImpl;
import by.training.hotel.model.dao.impl.RoleDaoImpl;
import by.training.hotel.model.dao.impl.RoomDaoImpl;
import by.training.hotel.model.dao.impl.RoomTypeDaoImpl;
import by.training.hotel.model.dao.impl.UserDaoImpl;
import by.training.hotel.model.di.ServiceCreator;
import by.training.hotel.model.pool.ConnectionPool;
import by.training.hotel.model.pool.ConnectionPoolException;
import by.training.hotel.model.di.ServiceCreationException;
import by.training.hotel.model.service.OrderService;
import by.training.hotel.model.service.RoleService;
import by.training.hotel.model.service.RoomService;
import by.training.hotel.model.service.RoomTypeService;
import by.training.hotel.model.service.impl.OrderServiceImpl;
import by.training.hotel.model.service.impl.RoleServiceImpl;
import by.training.hotel.model.service.impl.RoomServiceImpl;
import by.training.hotel.model.service.impl.RoomTypeServiceImpl;
import by.training.hotel.model.service.UserService;
import by.training.hotel.model.service.impl.UserServiceImpl;
import by.training.hotel.model.transaction.Transaction;
import by.training.hotel.model.transaction.impl.TransactionImpl;

public class ServiceCreatorImpl implements ServiceCreator {

    private RoleService roleService = null;
    public RoleService getRoleService() throws ServiceCreationException {
        if (roleService == null) {
            RoleServiceImpl roleServiceImpl = new RoleServiceImpl();
            roleServiceImpl.setRoleDao(getRoleDao());
            roleServiceImpl.setTransaction(getTransaction());
            roleService = roleServiceImpl;
        }
        return roleService;
    }

    private UserService userService = null;
    public UserService getUserService() throws ServiceCreationException {
        if (userService == null) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            userServiceImpl.setUserDao(getUserDao());
            userServiceImpl.setRoleDao(getRoleDao());
            userServiceImpl.setTransaction(getTransaction());
            userService = userServiceImpl;
        }
        return userService;
    }

    private RoomTypeService roomTypeService = null;
    public RoomTypeService getRoomTypeService() throws ServiceCreationException {
        if (roomTypeService == null) {
            RoomTypeServiceImpl roomTypeServiceImpl = new RoomTypeServiceImpl();
            roomTypeServiceImpl.setRoomTypeDao(getRoomTypeDao());
            roomTypeServiceImpl.setTransaction(getTransaction());
            roomTypeService = roomTypeServiceImpl;
        }
        return roomTypeService;
    }

    private RoomService roomService = null;
    public RoomService getRoomService() throws ServiceCreationException {
        if (roomService == null) {
            RoomServiceImpl roomServiceImpl = new RoomServiceImpl();
            roomServiceImpl.setRoomDao(getRoomDao());
            roomServiceImpl.setRoomTypeDao(getRoomTypeDao());
            roomServiceImpl.setTransaction(getTransaction());
            roomService = roomServiceImpl;
        }
        return roomService;
    }

    private OrderService orderService = null;
    public OrderService getOrderService() throws ServiceCreationException {
        if (orderService == null) {
            OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
            orderServiceImpl.setOrderDao(getOrderDao());
            orderServiceImpl.setRoomTypeDao(getRoomTypeDao());
            orderServiceImpl.setUserDao(getUserDao());
            orderServiceImpl.setRoomDao(getRoomDao());
            orderServiceImpl.setTransaction(getTransaction());
            orderService = orderServiceImpl;
        }
        return orderService;
    }

    private RoleDao roleDao = null;
    public RoleDao getRoleDao() throws ServiceCreationException {
        if (roleDao == null) {
            RoleDaoImpl roleDaoImpl = new RoleDaoImpl();
            roleDaoImpl.setConnection(getConnection());
            roleDao = roleDaoImpl;
        }
        return roleDao;
    }

    private UserDao userDao = null;
    public UserDao getUserDao() throws ServiceCreationException {
        if (userDao == null) {
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            userDaoImpl.setConnection(getConnection());
            userDao = userDaoImpl;
        }
        return userDao;
    }

    private RoomTypeDao roomTypeDao = null;
    public RoomTypeDao getRoomTypeDao() throws ServiceCreationException {
        if (roomTypeDao == null) {
            RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
            roomTypeDaoImpl.setConnection(getConnection());
            roomTypeDao = roomTypeDaoImpl;
        }
        return roomTypeDao;
    }

    private RoomDao roomDao = null;
    public RoomDao getRoomDao() throws ServiceCreationException {
        if (roomDao == null) {
            RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
            roomDaoImpl.setConnection(getConnection());
            roomDao = roomDaoImpl;
        }
        return roomDao;
    }

    private OrderDao orderDao = null;
    public OrderDao getOrderDao() throws ServiceCreationException {
        if (orderDao == null) {
            OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
            orderDaoImpl.setConnection(getConnection());
            orderDao = orderDaoImpl;
        }
        return orderDao;
    }

    public Transaction getTransaction() throws ServiceCreationException {
        TransactionImpl transaction = new TransactionImpl();
        transaction.setConnection(getConnection());
        return transaction;
    }

    private Connection connection = null;
    public Connection getConnection() throws ServiceCreationException {
        if (connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (ConnectionPoolException e) {
                throw new ServiceCreationException(e);
            }
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (Exception e) {}
    }

}
