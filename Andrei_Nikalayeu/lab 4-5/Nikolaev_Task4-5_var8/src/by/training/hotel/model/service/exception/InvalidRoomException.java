package by.training.hotel.model.service.exception;

import by.training.hotel.model.service.ServiceException;

public class InvalidRoomException extends ServiceException{
    
    public InvalidRoomException(String message) {
        super(message);
    }

    public InvalidRoomException(Throwable cause) {
        super(cause);
    }

}
