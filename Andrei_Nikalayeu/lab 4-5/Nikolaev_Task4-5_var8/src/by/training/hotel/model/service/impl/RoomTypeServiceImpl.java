package by.training.hotel.model.service.impl;

import java.util.List;

import by.training.hotel.model.dao.DaoException;
import by.training.hotel.model.dao.RoomTypeDao;
import by.training.hotel.model.domain.room.RoomType;
import by.training.hotel.model.service.RoomTypeService;
import by.training.hotel.model.service.ServiceException;

public class RoomTypeServiceImpl extends BaseService implements RoomTypeService {
    private RoomTypeDao roomTypeDao;

    public void setRoomTypeDao(RoomTypeDao roomTypeDao) {
        this.roomTypeDao = roomTypeDao;
    }

    @Override
    public List<RoomType> findAll() throws ServiceException {
        try {
            return roomTypeDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RoomType findById(Long id) throws ServiceException {
        try {
            return roomTypeDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(RoomType roomType) throws ServiceException {
        try {
            getTransaction().start();
            if (roomType.getId() != null) {
                roomTypeDao.update(roomType);
            } else {
                roomTypeDao.create(roomType);
            }
            getTransaction().commit();
        } catch (DaoException e) {
            getTransaction().rollback();
            throw new ServiceException(e);
        }
    }
}
