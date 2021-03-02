package by.training.hotel.model.service.exception;

import by.training.hotel.model.service.ServiceException;

public class RoomNumberNotUniqueException extends ServiceException {
    private String login;

    public RoomNumberNotUniqueException(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}

