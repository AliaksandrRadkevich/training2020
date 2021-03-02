package by.epam.jvd.vitebsk.task4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.user.UserService;

public class LoginAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            try {
                UserService userService = getServiceFactory().getUserService();
                User user = userService.findByLoginAndPassword(login, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("currentUser", user);
                    return new Forward("/index.html");
                } else {
                    return new Forward("/login.html?message=login.message.incorrect.password");
                }
            } catch (ServiceFactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
