package by.training.hotel.model.service.exception;

import by.training.hotel.model.service.ServiceException;

public class UserNotExistsException extends ServiceException {
    private Long id;

    public UserNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
