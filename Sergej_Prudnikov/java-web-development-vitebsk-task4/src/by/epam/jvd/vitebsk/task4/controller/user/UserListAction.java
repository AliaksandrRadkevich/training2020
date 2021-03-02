package by.epam.jvd.vitebsk.task4.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jvd.vitebsk.task4.controller.Action;
import by.epam.jvd.vitebsk.task4.controller.Forward;
import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.user.UserService;

public class UserListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = getServiceFactory().getUserService();
            List<User> users = userService.findAll();
            req.setAttribute("users", users);
            return null;
        } catch (ServiceFactoryException | ServiceException e) {
            throw new ServletException(e);
        } catch (Exception e1) {
            throw new ServletException(e1);
        }
    }
}
