package by.training.hotel.model.service.exception;

import by.training.hotel.model.service.ServiceException;

public class InvalidDateException extends ServiceException{
    
    public InvalidDateException(String message) {
        super(message);
    }

    public InvalidDateException(Throwable cause) {
        super(cause);
    }

}
