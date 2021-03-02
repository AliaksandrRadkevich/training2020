package hospitalProject.by.epam.web;

import java.io.IOException;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.Set;

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

public class SaveServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        try(ServiceCreator creator = new ServiceCreator()){
            UserService service = creator.getUserService();
            for(User u : service.findAll()) {
                if(login.equals(u.getLogin())) {
                    resp.sendRedirect(req.getContextPath() + "/signup.html?message="
                            + "User \"" + login + "\" already exists");
                    return;
                }
            }
        }
        catch(ServiceCreatorException | ServiceException e) {
            throw new ServletException(e);
        }
        
        String password = req.getParameter("password");
        Role role = null;
        try {
            role = Role.valueOf(req.getParameter("role"));
        }
        catch(IllegalFormatException | NullPointerException e) {}
        if(login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
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
            try (ServiceCreator creator = new ServiceCreator()){
                UserService service = creator.getUserService();
                service.save(user);
            }
            catch(ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html?message=User \"" + login + "\" has been successfully created");
    }
}
