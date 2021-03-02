package by.training.hotel.controller.actions.user;

import java.io.IOException;

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
import by.training.hotel.model.service.exception.UserLoginNotUniqueException;

public class UserSaveAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Role role = null;
        Long id = null;

        try {
            RoleService roleService = getServiceCreator().getRoleService();
            role = roleService.findById(Long.parseLong(req.getParameter("role"))); 
        } catch (NullPointerException | IllegalArgumentException | ServiceException | ServiceCreationException e) {}

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {}

        if (name != null & !name.isEmpty() && surname != null & !surname.isEmpty() && login != null & !login.isEmpty()
                && password != null & !password.isEmpty() && role != null) {

            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);

            try {
                UserService userService = getServiceCreator().getUserService();
                userService.save(user);
            } catch (UserLoginNotUniqueException e) {
                return new Forward("/user/edit.html?" + ((id != null) ? "id=" + id + "&" : "")
                        + "message=user.edit.message.edit.login.error");
            } catch (ServiceCreationException | ServiceException e) {
                throw new ServletException(e);
            }
        } else {
            return new Forward("/user/edit.html?" + ((id != null) ? "id=" + id + "&" : "")
                    + "message=user.edit.message.edit.error");
        }
        return new Forward("/user/list.html?message=user.edit.message.edit.success");
    }
}
