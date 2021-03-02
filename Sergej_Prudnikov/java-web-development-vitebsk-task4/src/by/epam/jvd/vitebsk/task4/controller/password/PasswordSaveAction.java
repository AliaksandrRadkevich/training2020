package by.epam.jvd.vitebsk.task4.controller.password;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jvd.vitebsk.task4.controller.Action;
import by.epam.jvd.vitebsk.task4.controller.Forward;
import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.user.UserService;

public class PasswordSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("old-password");
        String newPassword = req.getParameter("new-password");
        String newPasswordRepeat = req.getParameter("new-password-repeat");
        if (oldPassword != null && newPassword != null && newPasswordRepeat != null
                && newPassword.equals(newPasswordRepeat)) {
            try {
                UserService userService = getServiceFactory().getUserServiceWithTransaction();
                User user = (User) req.getSession(false).getAttribute("currentUser");
                userService.changePassword(user.getId(), oldPassword, newPassword);
            } catch (ServiceFactoryException | ServiceException e) {
                throw new ServletException(e);
            } catch (Exception e1) {
                throw new ServletException(e1);
            }
        }
        return new Forward("/index.html");
    }
}
