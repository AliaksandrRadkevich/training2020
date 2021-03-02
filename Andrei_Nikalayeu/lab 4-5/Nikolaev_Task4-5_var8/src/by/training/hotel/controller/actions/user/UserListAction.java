package by.training.hotel.controller.actions.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.hotel.controller.Forward;
import by.training.hotel.controller.actions.Action;
import by.training.hotel.model.domain.user.User;
import by.training.hotel.model.di.ServiceCreationException;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.UserService;

public class UserListAction extends Action{

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = getServiceCreator().getUserService();
            List<User> users = userService.findAll();
            req.setAttribute("users", users);

            return null;
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
