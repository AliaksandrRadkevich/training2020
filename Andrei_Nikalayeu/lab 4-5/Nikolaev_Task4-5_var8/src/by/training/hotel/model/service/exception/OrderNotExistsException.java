package by.training.hotel.model.service.exception;

import by.training.hotel.model.service.ServiceException;

public class OrderNotExistsException extends ServiceException {
    private Long id;

    public OrderNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
