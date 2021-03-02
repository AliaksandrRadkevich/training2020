package hospitalProject.by.epam.web.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hospitalProject.by.epam.di.ServiceCreator;
import hospitalProject.by.epam.di.ServiceCreatorException;
import hospitalProject.by.epam.domain.Role;
import hospitalProject.by.epam.domain.User;
import hospitalProject.by.epam.pool.ConnectionPool;
import hospitalProject.by.epam.pool.ConnectionPoolException;
import hospitalProject.by.epam.service.ServiceException;
import hospitalProject.by.epam.service.UserService;

public class UserListServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServiceCreator creator = new ServiceCreator()){
            UserService service = creator.getUserService();
            List<User> users = service.findAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
            return;
        }
        catch(ServiceCreatorException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
