package by.training.hotel.model.service;

import java.util.List;

import by.training.hotel.model.domain.room.Room;

public interface RoomService{
    List<Room> findAll() throws ServiceException;   
    Room findById(Long id) throws ServiceException;
    void save(Room room) throws ServiceException;
    void delete(List<Long> ids) throws ServiceException;
}
