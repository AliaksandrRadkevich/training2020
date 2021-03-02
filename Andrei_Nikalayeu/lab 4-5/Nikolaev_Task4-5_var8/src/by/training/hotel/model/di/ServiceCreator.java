package by.training.hotel.model.di;

import java.sql.Connection;

import by.training.hotel.model.dao.OrderDao;
import by.training.hotel.model.dao.RoleDao;
import by.training.hotel.model.dao.RoomDao;
import by.training.hotel.model.dao.RoomTypeDao;
import by.training.hotel.model.dao.UserDao;
import by.training.hotel.model.service.OrderService;
import by.training.hotel.model.service.RoleService;
import by.training.hotel.model.service.RoomService;
import by.training.hotel.model.service.RoomTypeService;
import by.training.hotel.model.service.UserService;
import by.training.hotel.model.transaction.Transaction;

public interface ServiceCreator extends AutoCloseable{

    RoleService getRoleService() throws ServiceCreationException;
    
    UserService getUserService() throws ServiceCreationException;
    
    RoomTypeService getRoomTypeService() throws ServiceCreationException;
    
    RoomService getRoomService() throws ServiceCreationException;
    
    OrderService getOrderService() throws ServiceCreationException;
    
    RoleDao getRoleDao() throws ServiceCreationException;
    
    UserDao getUserDao() throws ServiceCreationException;
    
    RoomTypeDao getRoomTypeDao() throws ServiceCreationException;
    
    RoomDao getRoomDao() throws ServiceCreationException;
    
    OrderDao getOrderDao() throws ServiceCreationException;
    
    Transaction getTransaction() throws ServiceCreationException;
    
    Connection getConnection() throws ServiceCreationException;
        
    void close();

}
