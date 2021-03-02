package by.training.hotel.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.hotel.controller.Forward;
import by.training.hotel.model.domain.user.User;
import by.training.hotel.model.di.ServiceCreationException;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.UserService;

public class LoginAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {

            try {
                UserService userService = getServiceCreator().getUserService();
                User user = userService.login(login, password);

                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("session_user", user);

                    return new Forward("/index.html");
                } else {
                    return new Forward("/login.html?message=login.message.incorrect.password");
                }
            } catch (ServiceCreationException | ServiceException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
