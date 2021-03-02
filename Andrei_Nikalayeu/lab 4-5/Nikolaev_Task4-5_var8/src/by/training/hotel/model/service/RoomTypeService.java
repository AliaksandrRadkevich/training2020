package by.training.hotel.model.service;

import java.util.List;

import by.training.hotel.model.domain.room.RoomType;

public interface RoomTypeService {
    List<RoomType> findAll() throws ServiceException;
    RoomType findById(Long id) throws ServiceException;
    void save(RoomType roomType) throws ServiceException;
}
