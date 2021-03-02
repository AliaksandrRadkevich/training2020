package by.training.hotel.model.service.exception;

import by.training.hotel.model.service.ServiceException;

public class RoomNotExistsException extends ServiceException {
    private Long id;

    public RoomNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
