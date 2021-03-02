package by.training.hotel.model.dao;

import java.util.List;

import by.training.hotel.model.domain.room.RoomType;

public interface RoomTypeDao extends Dao<RoomType>{
    List<RoomType> readAll() throws DaoException;
}
