package by.training.hotel.model.dao;

import java.util.List;

import by.training.hotel.model.domain.room.Room;

public interface RoomDao extends Dao<Room>{
    List<Room> readAll() throws DaoException;
    Room readByRoomNumber(String roomNumber) throws DaoException;
}
