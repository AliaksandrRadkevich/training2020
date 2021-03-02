package hospitalProject.by.epam.web.user;

import java.io.IOException;
import java.util.List;

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

public class UserEditServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null) {
            try (ServiceCreator creator = new ServiceCreator()){
                UserService service = creator.getUserService();
                User user = service.findById(Long.parseLong(id));
                if(user != null) {
                    req.setAttribute("user", user);
                }
                else {
                    throw new IllegalArgumentException();
                }
            }
            catch(ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            }
            catch(IllegalArgumentException e) {
                resp.sendError(404);
                return;
            }
        }
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
    }
}
