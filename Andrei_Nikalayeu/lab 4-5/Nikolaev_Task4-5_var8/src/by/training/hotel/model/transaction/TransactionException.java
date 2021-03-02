package by.training.hotel.model.transaction;

import by.training.hotel.model.service.ServiceException;

public class TransactionException extends ServiceException{
    public TransactionException(Throwable cause) {
        super(cause);
    }
}
