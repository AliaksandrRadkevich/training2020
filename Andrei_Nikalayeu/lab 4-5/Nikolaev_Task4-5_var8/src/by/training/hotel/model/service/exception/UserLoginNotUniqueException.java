package by.training.hotel.model.service.exception;

import by.training.hotel.model.service.ServiceException;

public class UserLoginNotUniqueException extends ServiceException {
    private String login;

    public UserLoginNotUniqueException(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}

