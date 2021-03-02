package hospitalProject.by.epam.web.user;

import java.io.IOException;
import java.util.IllegalFormatException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hospitalProject.by.epam.di.ServiceCreator;
import hospitalProject.by.epam.di.ServiceCreatorException;
import hospitalProject.by.epam.domain.Role;
import hospitalProject.by.epam.domain.User;
import hospitalProject.by.epam.service.ServiceException;
import hospitalProject.by.epam.service.UserService;

public class UserSaveServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        Role role = null;
        try {
            role = Role.valueOf(req.getParameter("role"));
        }
        catch(IllegalFormatException | NullPointerException e) {}
        if(login != null && !login.isEmpty() && password != null) {
            Long id = null;
            try {
                id = Long.parseLong(req.getParameter("id"));
            }
            catch(NumberFormatException e) {}
            User user = new User();
            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);
            user.setRole(role);
            try (ServiceCreator creator = new ServiceCreator();){
                UserService service = creator.getUserService();
                service.save(user);
            }
            catch(ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/user/list.html");
    }
}
