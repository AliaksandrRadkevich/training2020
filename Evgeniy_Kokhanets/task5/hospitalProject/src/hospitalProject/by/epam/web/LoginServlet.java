package hospitalProject.by.epam.web;

import java.io.IOException;
import java.net.URLEncoder;

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

public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(login != null && password != null) {
            try(ServiceCreator creator = new ServiceCreator()){
                UserService service = creator.getUserService();
                User user = service.login(login, password);
                if(user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("session_user", user);
                    resp.sendRedirect(req.getContextPath() + "/index.html");
                    return;
                }
            }
            catch(ServiceCreatorException | ServiceException | NullPointerException e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html?message=" + URLEncoder.encode("Invalid user", "UTF-8"));
    }
}
