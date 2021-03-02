package hospitalProject.by.epam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hospitalProject.by.epam.domain.Role;
import hospitalProject.by.epam.domain.User;

public class SettingsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User)session.getAttribute("session_user");
            if(user != null) {
                req.setAttribute("login", user.getLogin());
                req.setAttribute("userRole", user.getRole());
                req.setAttribute("roles", Role.values());
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/jsp/settings.jsp").forward(req, resp);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
