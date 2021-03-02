package by.epam.jvd.vitebsk.task4.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jvd.vitebsk.task4.controller.Action;
import by.epam.jvd.vitebsk.task4.controller.Forward;

import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.Role;
import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.user.UserService;

public class UserSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
        }
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        try {
            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        }
        if (user.getLogin() != null && user.getRole() != null && user.getEmail() != null) {
            try {
                UserService userService = getServiceFactory().getUserServiceWithTransaction();
                userService.save(user);
            } catch (ServiceFactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/user/list.html");
    }
}