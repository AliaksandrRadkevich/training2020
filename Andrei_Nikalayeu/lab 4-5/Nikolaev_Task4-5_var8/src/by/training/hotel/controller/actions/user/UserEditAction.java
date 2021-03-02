package by.training.hotel.controller.actions.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.hotel.controller.Forward;
import by.training.hotel.controller.actions.Action;
import by.training.hotel.model.domain.user.Role;
import by.training.hotel.model.domain.user.User;
import by.training.hotel.model.di.ServiceCreationException;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.RoleService;
import by.training.hotel.model.service.UserService;

public class UserEditAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<Role> roles = null;

        try {
            RoleService roleService = getServiceCreator().getRoleService();
            roles = roleService.findAll();
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }

        if (id != null) {
            try {

                UserService userService = getServiceCreator().getUserService();
                User user = userService.findById(Long.parseLong(id));

                if (user != null) {
                    req.setAttribute("user", user);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (ServiceCreationException | ServiceException e) {
                throw new ServletException(e);
            } catch (IllegalArgumentException e) {
                resp.sendError(404);
                return null;
            }
        }
        req.setAttribute("roles", roles);
        return null;
    }
}
