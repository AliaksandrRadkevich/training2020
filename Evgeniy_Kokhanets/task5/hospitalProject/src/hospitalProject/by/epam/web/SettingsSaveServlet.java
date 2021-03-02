package hospitalProject.by.epam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hospitalProject.by.epam.di.ServiceCreator;
import hospitalProject.by.epam.di.ServiceCreatorException;
import hospitalProject.by.epam.domain.User;
import hospitalProject.by.epam.service.ServiceException;
import hospitalProject.by.epam.service.UserService;

public class SettingsSaveServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User)session.getAttribute("session_user");
            if(user != null && name != null && !name.isEmpty() && password != null && !password.isEmpty()) {
                try(ServiceCreator creator = new ServiceCreator()) {
                    UserService service = creator.getUserService();
                    User updateUser = new User();
                    updateUser.setId(user.getId());
                    updateUser.setLogin(user.getLogin());
                    updateUser.setName(name);
                    updateUser.setPassword(password);
                    updateUser.setRole(user.getRole());
                    service.save(updateUser);
                    resp.sendRedirect(req.getContextPath() + String.format("/logout.html?message="
                            + "User '%s' has been successfully updated", user.getLogin()));
                    return;
                }
                catch(ServiceCreatorException | ServiceException e) {
                    throw new ServletException(e);
                }
            }
            resp.sendRedirect(req.getContextPath() + String.format("/settings.html?message="
                    + "Couldn't update user '%s'", user.getLogin()));
        }
    }
}
