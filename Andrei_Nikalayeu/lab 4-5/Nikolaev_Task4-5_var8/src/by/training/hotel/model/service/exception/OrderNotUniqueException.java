package by.training.hotel.model.service.exception;

import by.training.hotel.model.service.ServiceException;

public class OrderNotUniqueException extends ServiceException {
    private Long id;

    public OrderNotUniqueException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

