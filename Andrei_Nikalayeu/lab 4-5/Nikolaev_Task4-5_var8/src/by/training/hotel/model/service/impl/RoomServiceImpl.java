package by.training.hotel.model.service.impl;

import java.util.List;

import by.training.hotel.model.dao.DaoException;
import by.training.hotel.model.dao.RoomDao;
import by.training.hotel.model.dao.RoomTypeDao;
import by.training.hotel.model.domain.room.Room;
import by.training.hotel.model.domain.room.RoomType;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.exception.RoomNotExistsException;
import by.training.hotel.model.service.exception.RoomNumberNotUniqueException;
import by.training.hotel.model.service.RoomService;

public class RoomServiceImpl extends BaseService implements RoomService{
    private RoomDao roomDao;
    private RoomTypeDao roomTypeDao;
    
    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }
    
    public void setRoomTypeDao(RoomTypeDao roomTypeDao) {
        this.roomTypeDao = roomTypeDao;
    }
    

    @Override
    public List<Room> findAll() throws ServiceException {
        try {
            List<Room> rooms = roomDao.readAll();
            
            for (Room room : rooms) {

                RoomType roomType  = room.getRoomType();
                roomType = roomTypeDao.read(room.getRoomType().getId());
                room.setRoomType(roomType);
                
            }

            return rooms;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    
    @Override
    public Room findById(Long id) throws ServiceException {
        try {
            Room room = roomDao.read(id);

            RoomType roomType  = room.getRoomType();
            roomType = roomTypeDao.read(room.getRoomType().getId());
            room.setRoomType(roomType);

            return room;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Override
    public void save(Room room) throws ServiceException {
        try {
            getTransaction().start();
            
            if (room.getId() != null) {
                Room storedRoom = roomDao.read(room.getId());
                if(storedRoom != null) {
                    if(storedRoom.getRoomNumber().equals(room.getRoomNumber()) || roomDao.readByRoomNumber(room.getRoomNumber()) == null) {
                        roomDao.update(room);
                    } else {
                        throw new RoomNumberNotUniqueException(room.getRoomNumber());
                    }
                } else {
                    throw new RoomNotExistsException(room.getId());
                }
            } else {
                if(roomDao.readByRoomNumber(room.getRoomNumber()) == null) {
                    Long id = roomDao.create(room);
                    room.setId(id);
                } else {
                    throw new RoomNumberNotUniqueException(room.getRoomNumber());
                }
            }
            
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
                roomDao.delete(id);
            }
            
            getTransaction().commit();
        } catch (DaoException e) {
            getTransaction().rollback(); 
            throw new ServiceException(e);
        }
    }

}
