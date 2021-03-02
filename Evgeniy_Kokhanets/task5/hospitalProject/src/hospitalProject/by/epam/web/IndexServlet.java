package hospitalProject.by.epam.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hospitalProject.by.epam.domain.User;

public class IndexServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User)session.getAttribute("session_user");
            if(user != null) {
                switch(user.getRole()) {
                case ADMIN:
                    resp.sendRedirect(req.getContextPath() + "/user/list.html");
                    return;
                case PATIENT: 
                    resp.sendRedirect(req.getContextPath() + "/prescription/list.html?message=" + URLEncoder.encode
                            (String.format("User '%s' has been successfully authorized", user.getLogin()), "UTF-8"));
                    return;
                case PHYSICIAN:
                    resp.sendRedirect(req.getContextPath() + "/prescription/list.html?message=" + URLEncoder.encode
                            (String.format("User '%s' has been successfully authorized", user.getLogin()), "UTF-8"));
                    return;
                case NURSE: 
                    resp.sendRedirect(req.getContextPath() + "/patient/find.html?message=" + URLEncoder.encode
                            (String.format("User '%s' has been successfully authorized", user.getLogin()), "UTF-8"));
                    return;
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
