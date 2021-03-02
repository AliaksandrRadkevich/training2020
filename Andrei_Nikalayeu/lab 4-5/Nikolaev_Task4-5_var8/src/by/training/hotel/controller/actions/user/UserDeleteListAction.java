package by.training.hotel.controller.actions.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.hotel.controller.Forward;
import by.training.hotel.controller.actions.Action;
import by.training.hotel.model.domain.user.User;
import by.training.hotel.model.di.ServiceCreationException;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.UserService;

public class UserDeleteListAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String idsStr[] = req.getParameterValues("id");
        List<User> users = null;

        if (idsStr != null) {
            users = new ArrayList<User>(idsStr.length);

            try {
                UserService userService = getServiceCreator().getUserService();

                for (String id : idsStr) {
                    users.add(userService.findById(Long.valueOf(id)));
                }

                if (session != null) {
                    User session_user = (User) session.getAttribute("session_user");
                    if (session_user != null) {
                        users.remove(userService.findById(session_user.getId()));
                    }
                }

                req.setAttribute("users", users);
            } catch (ServiceCreationException | ServiceException | NumberFormatException e) {
                throw new ServletException(e);
            }

            return null;
        } else {
            return new Forward("/user/list.html?message=user.delete.list.message.delete.empty");
        }
    }
}
